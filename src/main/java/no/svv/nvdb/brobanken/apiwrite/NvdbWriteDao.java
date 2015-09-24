package no.svv.nvdb.brobanken.apiwrite;

import no.svv.nvdb.brobanken.Config;
import no.svv.nvdb.brobanken.apiwrite.data.*;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class NvdbWriteDao {

    private LoginState loginState;

    public String createChangeset(String username, String password, Endringssett endringssett) {

        // Login on OpenAM
        LoginState loginState = login(username, password);

        // Creates a REST client
        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));

        // Uploads / creates the changeset
        Ressurser ressurser = client.target(Config.instance.get("url.skriv") + "/endringssett")
                .request()
                .accept("application/json")
                .cookie(loginState.getSsoCookieName(), loginState.getSsoToken())
                .post(Entity.json(endringssett), Ressurser.class);

        // Reads response and extracts the url to the endpoint for starting processing of a changeset
        String startUri = ressurser.getRessurser().stream().filter(r -> r.getRel().equals("start")).findAny().get().getSrc();

        // Calls the endpoint to start processing the changeset
        client.target(startUri)
                .request()
                .accept("application/json")
                .cookie(loginState.getSsoCookieName(), loginState.getSsoToken())
                .post(Entity.json(null), String.class);

        return extractChangeSetId(startUri);
    }

    public Status readStatus(String changeSetId, String username, String password) {

        // Login on OpenAM
        LoginState loginState = login(username, password);

        // Creates a REST client
        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));

        // Calls the endpoint to get the current status for the changeset
        return client.target(Config.instance.get("url.skriv") + "/endringssett/" + changeSetId + "/status")
                .request()
                .accept("application/json")
                .cookie(loginState.getSsoCookieName(), loginState.getSsoToken())
                .get().readEntity(Status.class);

    }

    private String extractChangeSetId(String startUri) {
        Pattern pattern = Pattern.compile(".*/([^/]*)/start");
        Matcher m = pattern.matcher(startUri);
        if (m.matches()) {
            return m.group(1);
        } else {
            throw new IllegalArgumentException("Did not find a changeset id in th start uri: " + startUri);
        }
    }

    private LoginState login(String username, String password) {
        if (loginState == null) {
            loginState = new Authentication().login(username, password, Config.instance.get("url.loginserver"));
        }
        return loginState;
    }


}
