package no.svv.nvdb.brobanken;

import no.svv.nvdb.brobanken.apiwrite.NvdbWriteDao;
import no.svv.nvdb.brobanken.apiwrite.data.Status;
import no.svv.nvdb.brobanken.representation.Bridge;
import no.svv.nvdb.brobanken.representation.Job;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * The REST endpoint for reading jobs.
 * <p/>
 * User: Sigurd Stendal
 * Date: 12.02.15
 */
@Path("job")
public class JobService {

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response get(@HeaderParam("X-username") String username, @HeaderParam("X-password") String password) {

        NvdbWriteDao nvdbWriteDao = new NvdbWriteDao();

        // Updates status of the job if present
        if(JobRegister.instance.isPresent()) {
            Job job = JobRegister.instance.get();
            String id = job.getId();
            String fremdrift = nvdbWriteDao.readStatus(id, username, password).getFremdrift();
            job.setStatus(fremdrift);
        }

        if(JobRegister.instance.isPresent()) {
            return Response.ok().entity(JobRegister.instance.get()).build();
        } else {
            return Response.ok().build();
        }

    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("create")
    public Response create(List<Bridge> bridges, @HeaderParam("X-username") String username, @HeaderParam("X-password") String password) {

        try {
            Job job = new Job(null, null, bridges);

            JobRegister.instance = Optional.of(job);

            NvdbWriteDao dao = new NvdbWriteDao();

            String jobId = dao.createJob(bridges, username, password);
            job.setId(jobId);

            Status status = dao.readStatus(jobId, username, password);
            job.setStatus(status.getFremdrift());

            return Response.ok().entity(job).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
