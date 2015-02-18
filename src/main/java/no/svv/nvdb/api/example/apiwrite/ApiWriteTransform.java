package no.svv.nvdb.api.example.apiwrite;

import no.svv.nvdb.api.example.apiread.data.VegObjekter;
import no.svv.nvdb.api.example.apiread.data.Veglenke;
import no.svv.nvdb.api.example.apiwrite.data.*;
import no.svv.nvdb.api.example.representation.Bridge;
import no.svv.nvdb.api.example.representation.Job;

/**
 * Transforms objects from the domain model used this applications internal domain model (and in API Read) to the
 * domain model used in API Write.
 * <p/>
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class ApiWriteTransform {

    public static VegObjekt toVegObjekt(Bridge bridge, VegObjekter original) {

        Veglenke veglenke = original.getLokasjon().getVeglenker().get(0);

        VegObjekt vegObjekt = new VegObjekt(60, bridge.getObjektId());
        vegObjekt.setVersjon(original.getVersjonsId());
        vegObjekt.setLokasjon(new Lokasjon(new NettElementer(new Linje(veglenke.getId(), veglenke.getFra(), veglenke.getTil()))));
        original.getEgenskaper().forEach(vo -> vegObjekt.getEgenskaper().add(new Egenskap(vo.getId(), vo.getVerdi())));

        vegObjekt.getEgenskaper().forEach(vo -> {
            if (vo.getTypeId().equals("1080")) {
                vo.setVerdi(bridge.getName());
            }
        });

        // TODO: Lokasjon

        return vegObjekt;
    }

    public static Job toJob(String id, Status status) {
        return new Job(id, status.getFremdrift());
    }


}
