package no.svv.nvdb.brobanken;

import no.svv.nvdb.brobanken.apiread.ApiReadTransform;
import no.svv.nvdb.brobanken.apiread.NvdbReadDao;
import no.svv.nvdb.brobanken.apiwrite.NvdbWriteDao;
import no.svv.nvdb.brobanken.apiwrite.data.Status;
import no.svv.nvdb.brobanken.representation.Bbox;
import no.svv.nvdb.brobanken.representation.Bridge;
import no.svv.nvdb.brobanken.representation.Job;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
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
    public Response updateBridges(List<Bridge> bridges) {

        Job job = new Job(null, null, bridges);

        JobRegister.instance = Optional.of(job);

        NvdbWriteDao dao = new NvdbWriteDao();

        String jobId = dao.createJob(bridges);
        job.setId(jobId);

        Status status = dao.readStatus(jobId);
        job.setStatus(status.getFremdrift());

        return Response.ok().entity(job).build();
    }

}
