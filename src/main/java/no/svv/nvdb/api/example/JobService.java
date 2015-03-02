package no.svv.nvdb.api.example;

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
    public Response getJob() {

        NvdbWriteDao nvdbWriteDao = new NvdbWriteDao();

        // Updates status of the job if present
        if(JobRegister.instance.isPresent()) {
            Job job = JobRegister.instance.get();
            String id = job.getId();
            String fremdrift = nvdbWriteDao.readStatus(id).getFremdrift();
            job.setStatus(fremdrift);
        }

        if(JobRegister.instance.isPresent()) {
            return Response.ok().entity(JobRegister.instance.get()).build();
        } else {
            return Response.ok().build();
        }


    }

}
