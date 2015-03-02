package no.svv.nvdb.api.example.apiwrite;

import no.svv.nvdb.api.example.apiread.data.VegObjekter;
import no.svv.nvdb.api.example.apiread.data.Veglenke;
import no.svv.nvdb.api.example.apiwrite.data.*;
import no.svv.nvdb.api.example.representation.Bridge;

/**
 * Transforms objects from the domain model used this applications internal domain model (and in API Read) to the
 * domain model used in API Write.
 * <p/>
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class ApiWriteTransform {

    public static VegObjekt2 toVegObjekt(Bridge bridge, VegObjekter original) {

        Veglenke veglenkeFromApiRead = original.getLokasjon().getVeglenker().get(0);

        // Copy values from API read
        VegObjekt2 vegObjekt = new VegObjekt2(60, bridge.getObjektId());
        vegObjekt.setVersjon(original.getVersjonsId());
        vegObjekt.setLokasjon(new Lokasjon(new Linje(veglenkeFromApiRead.getId(), veglenkeFromApiRead.getFra(), veglenkeFromApiRead.getTil())));
        original.getEgenskaper().forEach(vo -> vegObjekt.getEgenskaper().add(new Egenskap(vo.getId(), vo.getVerdi())));

        // Overwrites with updated value for name
        vegObjekt.getEgenskaper().forEach(vo -> {
            if (vo.getTypeId().equals("1080")) {
                vo.getVerdi().clear();
                vo.getVerdi().add(bridge.getName());
            }
        });

        return vegObjekt;
    }

}
