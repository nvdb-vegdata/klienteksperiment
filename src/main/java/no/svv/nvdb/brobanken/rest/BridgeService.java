package no.svv.nvdb.brobanken.rest;

import no.svv.nvdb.brobanken.apiread.ApiReadTransform;
import no.svv.nvdb.brobanken.apiread.NvdbReadDao;
import no.svv.nvdb.brobanken.representation.Bbox;
import no.svv.nvdb.brobanken.representation.Bridge;

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

                // Calls API Read
                .readBridges(bbox)
                .stream()

                // Transform the result from API Read to the representation used in this application
                .map(ApiReadTransform::toBridge)
                .collect(Collectors.toList());

        // Returns the list of bridges to the client
        return Response.ok().entity(bridges).build();
    }

}
