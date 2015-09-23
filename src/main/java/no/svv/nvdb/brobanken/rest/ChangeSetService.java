package no.svv.nvdb.brobanken.rest;

import no.svv.nvdb.brobanken.model.Model;
import no.svv.nvdb.brobanken.apiread.NvdbReadDao;
import no.svv.nvdb.brobanken.apiread.data.Datakatalog;
import no.svv.nvdb.brobanken.apiread.data.VegObjekter;
import no.svv.nvdb.brobanken.apiwrite.ApiWriteTransform;
import no.svv.nvdb.brobanken.apiwrite.NvdbWriteDao;
import no.svv.nvdb.brobanken.apiwrite.data.Endringssett;
import no.svv.nvdb.brobanken.apiwrite.data.Oppdater;
import no.svv.nvdb.brobanken.apiwrite.data.Status;
import no.svv.nvdb.brobanken.apiwrite.data.VegObjekt;
import no.svv.nvdb.brobanken.representation.Bridge;
import no.svv.nvdb.brobanken.representation.ChangeSet;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * The REST endpoint for reading changesets.
 * <p/>
 * User: Sigurd Stendal
 * Date: 12.02.15
 */
@Path("changeset")
public class ChangeSetService {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("create")
    public Response create(List<Bridge> bridges, @HeaderParam("X-username") String username, @HeaderParam("X-password") String password) {

        try {

            // Set this changeset as the current changset in the model
            Model.setChangeSet(new ChangeSet(bridges));

            // Transform the changeset to a API Write Endringssett
            NvdbReadDao nvdbReadDao = new NvdbReadDao();
            Datakatalog datakatalog = nvdbReadDao.readDatakatalog();
            Endringssett endringssett = createNvdbWriteEndringssett(bridges, nvdbReadDao, datakatalog);

            // Send the changeset to API Write
            NvdbWriteDao dao = new NvdbWriteDao();
            String changeSetId = dao.createChangeset(username, password, endringssett);
            Model.getChangeSet().setId(changeSetId);

            // Read status for the changeset from API Write
            Status status = dao.readStatus(changeSetId, username, password);
            Model.getChangeSet().setStatus(status.getFremdrift());

            // Return the changeset back to the client
            return Response.ok().entity(Model.getChangeSet()).build();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response get(@HeaderParam("X-username") String username, @HeaderParam("X-password") String password) {

        NvdbWriteDao nvdbWriteDao = new NvdbWriteDao();

        // Reads status of the changset from API Write if present
        if(Model.hasChangeSet()) {
            String fremdrift = nvdbWriteDao.readStatus(Model.getChangeSet().getId(), username, password).getFremdrift();
            Model.getChangeSet().setStatus(fremdrift);
        }

        if(Model.hasChangeSet()) {
            return Response.ok().entity(Model.getChangeSet()).build();
        } else {
            return Response.ok().build();
        }

    }

    private Endringssett createNvdbWriteEndringssett(List<Bridge> bridges, NvdbReadDao nvdbReadDao, Datakatalog datakatalog) {
        Endringssett endringssett = new Endringssett();
        endringssett.setOppdater(new Oppdater());
        endringssett.setDatakatalogversjon(datakatalog.getVersion());

        bridges.forEach(bridge -> {
            VegObjekter objektFromNvdb = nvdbReadDao.readBridge(bridge.getObjektId());

            VegObjekt objektToWrite = ApiWriteTransform.toVegObjekt(bridge, objektFromNvdb);

            endringssett.getOppdater().getVegObjekter().add(objektToWrite);
        });
        return endringssett;
    }

}
