package no.svv.nvdb.api.example.apiread;

import no.svv.nvdb.api.example.apiread.data.SokeResultat;
import no.svv.nvdb.api.example.representation.Bbox;
import no.svv.nvdb.api.example.representation.Bridge;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Sigurd Stendal
 * Date: 12.02.15
 */
public class NvdbReadDao {

    private static final String SOK = "{\"lokasjon\":{\"bbox\":\"BBOXSTRING\"},\"objektTyper\":[{\"id\":60,\"antall\":99999999,\"filter\":[]}]}";
    private static final String DEFAULT_BBOX = "227203.4327098648, 7016350.6084574405, 313902.29281373497, 7067150.709044418";

    public List<Bridge> readBridges(Bbox bbox) {

        String bboxString = DEFAULT_BBOX;
        if (bbox != null) {
            bboxString = bbox.getSouthWest().getX() + ", " + bbox.getSouthWest().getY() + ", " + bbox.getNorthEast().getX() + ", " + bbox.getNorthEast().getY();
        }

        Client client = ClientBuilder.newClient();
        String url = "https://www.vegvesen.no/nvdb/api";
        SokeResultat sokeResultat = client.target(url)
                .path("sok")
                .queryParam("kriterie", urlEncode(SOK.replace("BBOXSTRING", bboxString)))
                .queryParam("select", urlEncode("objektId,objektTypeId,vegObjektLokasjon/geometriUtm33,navn"))
                .request()
                .accept("application/json")
                .get(SokeResultat.class);


        List<Bridge> bridges = sokeResultat.getResultater().stream()
                .filter(r -> r.getTypeId().equals(60))
                .findAny().get()                  // What if there are no bridges in this result set?
                .getVegObjekter()
                .stream()
                .map(vo -> Transform.toBridge(vo.getLokasjon().getGeometriUtm33(), vo.getNavn()))
                .collect(Collectors.toList());

        return bridges;


    }

    private static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


}
