package no.svv.nvdb.api.example;

import no.svv.nvdb.api.example.apiread.NvdbReadDao;
import no.svv.nvdb.api.example.representation.Bbox;
import no.svv.nvdb.api.example.representation.Bridge;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 12.02.15
 */
@Path("bridge")
public class BridgeService {


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response getBridges(Bbox bbox) {

        List<Bridge> bridges = new NvdbReadDao().readBridges(bbox);

        return Response.ok().entity(bridges).build();
    }


}
