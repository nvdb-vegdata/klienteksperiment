package no.svv.nvdb.api.example.apiread;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.svv.nvdb.api.example.Config;
import no.svv.nvdb.api.example.apiread.data.*;
import no.svv.nvdb.api.example.representation.Bbox;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.uri.UriComponent;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Data access object for reading data from API Read
 * <p/>
 * User: Sigurd Stendal
 * Date: 12.02.15
 */
public class NvdbReadDao {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //private static final String SOK = "{\"lokasjon\":{\"bbox\":\"BBOXSTRING\"},\"objektTyper\":[{\"id\":60,\"antall\":99999999,\"filter\":[]}]}";
    private static final String DEFAULT_BBOX = "227203.4327098648, 7016350.6084574405, 313902.29281373497, 7067150.709044418";

    private static final Sok SOK = new Sok(new Lokasjon(DEFAULT_BBOX), Arrays.asList(new ObjektType(60, 9999999L)));

    /**
     * Reads all bridges in the bounding box
     */
    public List<VegObjekter> readBridges(Bbox bbox) {

        String bboxString = DEFAULT_BBOX;
        if (bbox != null) {
            bboxString = bbox.getSouthWest().getX() + ", " + bbox.getSouthWest().getY() + ", " + bbox.getNorthEast().getX() + ", " + bbox.getNorthEast().getY();
        }

        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));

        String url = Config.instance.get("url.les");
        return client.target(url)
                .path("sok")
                .queryParam("kriterie", encodeParam(SOK))
                .request()
                .accept("application/json")
                .get(SokeResultat.class)
                .getResultater()
                .stream()
                .filter(r -> r.getTypeId().equals(60))   // We are only interested in the result set with type id 60
                .findAny().get()                         // We just assume that there is a result set with type id 60 since we asked for this
                .getVegObjekter();
    }

    /**
     * Reads all data for a single bridge
     */
    public VegObjekter readBridge(Number objektId) {

        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter());

        String url = Config.instance.get("url.les") + "/vegobjekter/objekt/" + objektId;
        return client.target(url)
                .request()
                .accept("application/json")
                .get(VegObjekter.class);

    }

    private static String encodeParam(Object value) {
        try {
            return UriComponent.encode(objectMapper.writeValueAsString(value), UriComponent.Type.QUERY_PARAM_SPACE_ENCODED);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
