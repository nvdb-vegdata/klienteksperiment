package no.svv.nvdb.api.example;

import no.svv.nvdb.api.example.apiwrite.ApiWriteTransform;
import no.svv.nvdb.api.example.apiwrite.NvdbWriteDao;
import no.svv.nvdb.api.example.representation.Job;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

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
    public Response getJobs() {

        NvdbWriteDao nvdbWriteDao = new NvdbWriteDao();

        List<Job> jobs = new LinkedList<>();

        JobRegister.instance.forEach(id -> jobs.add(ApiWriteTransform.toJob(id, nvdbWriteDao.readStatus(id))));

        return Response.ok().entity(jobs).build();
    }

}
