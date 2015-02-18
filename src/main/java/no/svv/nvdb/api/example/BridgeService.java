package no.svv.nvdb.api.example;

import no.svv.nvdb.api.example.apiread.ApiReadTransform;
import no.svv.nvdb.api.example.apiread.NvdbReadDao;
import no.svv.nvdb.api.example.apiwrite.NvdbWriteDao;
import no.svv.nvdb.api.example.representation.Bbox;
import no.svv.nvdb.api.example.representation.Bridge;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The REST endpoint for reading and updating bridges.
 * <p/>
 * User: Sigurd Stendal
 * Date: 12.02.15
 */
@Path("bridge")
public class BridgeService {


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("read")
    public Response getBridges(Bbox bbox) {

        List<Bridge> bridges = new NvdbReadDao()
                .readBridges(bbox)
                .stream()
                .map(ApiReadTransform::toBridge)
                .collect(Collectors.toList());

        return Response.ok().entity(bridges).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("update")
    public Response updateBridge(Bridge bridge) {

        String jobId = new NvdbWriteDao().writeBridgeName(bridge);
        JobRegister.instance.add(jobId);

        return Response.ok().entity(jobId).build();
    }

}
