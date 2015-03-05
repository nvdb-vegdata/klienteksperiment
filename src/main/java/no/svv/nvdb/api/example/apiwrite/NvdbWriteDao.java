package no.svv.nvdb.api.example.apiwrite;

import no.svv.nvdb.api.example.Config;
import no.svv.nvdb.api.example.apiread.NvdbReadDao;
import no.svv.nvdb.api.example.apiread.data.Datakatalog;
import no.svv.nvdb.api.example.apiread.data.VegObjekter;
import no.svv.nvdb.api.example.apiwrite.data.*;
import no.svv.nvdb.api.example.representation.Bridge;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class NvdbWriteDao {


    public String createJob(List<Bridge> bridges) {

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



        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));

        Ressurser ressurser = client.target(Config.instance.get("url.skriv") + "/api/jobber")
                .request()
                .accept("application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("extscs:secret".getBytes()))
                .post(Entity.json(jobb), Ressurser.class);

        String startUri = ressurser.getRessurser().stream().filter(r -> r.getRel().equals("start")).findAny().get().getSrc();

        client.target(startUri)
                .request()
                .accept("application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("extscs:secret".getBytes()))
                .post(Entity.json(null), String.class);

        return extractJobId(startUri);
    }

    public Status readStatus(String jobId) {

        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));

        System.out.println("Check status for job id: " + jobId);
        return client.target(Config.instance.get("url.skriv") + "/api/jobber/" + jobId + "/status")
                .request()
                .accept("application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("extscs:secret".getBytes()))
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


}
