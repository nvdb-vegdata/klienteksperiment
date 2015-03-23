package no.svv.nvdb.brobanken.apiwrite;

import no.svv.nvdb.brobanken.Config;
import no.svv.nvdb.brobanken.apiread.NvdbReadDao;
import no.svv.nvdb.brobanken.apiread.data.Datakatalog;
import no.svv.nvdb.brobanken.apiread.data.VegObjekter;
import no.svv.nvdb.brobanken.apiwrite.data.*;
import no.svv.nvdb.brobanken.representation.Bridge;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class NvdbWriteDao {

    private LoginState loginState;

    public String createJob(List<Bridge> bridges, String username, String password) {

        NvdbReadDao nvdbReadDao = new NvdbReadDao();

        Datakatalog datakatalog = nvdbReadDao.readDatakatalog();

        Jobb jobb = new Jobb();
        jobb.setOppdater(new Oppdater());
        jobb.setDatakatalogversjon(datakatalog.getVersion());

        bridges.forEach(bridge -> {
            VegObjekter objektFromNvdb = nvdbReadDao.readBridge(bridge.getObjektId());

            VegObjekt objektToWrite = ApiWriteTransform.toVegObjekt(bridge, objektFromNvdb);

            jobb.getOppdater().getVegObjekter().add(objektToWrite);
        });

        String ssoToken = getLoginState(username, password).getSsoToken();
        String ssoCookieName = getLoginState(username, password).getSsoCookieName();

        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));

        Ressurser ressurser = client.target(Config.instance.get("url.skriv") + "/api/jobber")
                .request()
                .accept("application/json")
                .cookie(ssoCookieName, ssoToken)
                .post(Entity.json(jobb), Ressurser.class);

        String startUri = ressurser.getRessurser().stream().filter(r -> r.getRel().equals("start")).findAny().get().getSrc();

        client.target(startUri)
                .request()
                .accept("application/json")
                .cookie(ssoCookieName, ssoToken)
                .post(Entity.json(null), String.class);

        return extractJobId(startUri);
    }

    public Status readStatus(String jobId, String username, String password) {

        String ssoToken = getLoginState(username, password).getSsoToken();
        String ssoCookieName = getLoginState(username, password).getSsoCookieName();

        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));

        System.out.println("Check status for job id: " + jobId);
        return client.target(Config.instance.get("url.skriv") + "/api/jobber/" + jobId + "/status")
                .request()
                .accept("application/json")
                .cookie(ssoCookieName, ssoToken)
                .get().readEntity(Status.class);

    }

    private String extractJobId(String startUri) {
        Pattern pattern = Pattern.compile(".*/([^/]*)/start");
        Matcher m = pattern.matcher(startUri);
        if (m.matches()) {
            return m.group(1);
        } else {
            throw new IllegalArgumentException("Did not find a job id in th start uri: " + startUri);
        }
    }

    private LoginState getLoginState(String username, String password) {
        if (loginState == null) {
            loginState = new Authentication().login(username, password, Config.instance.get("url.loginserver"));
        }
        return loginState;
    }


}
