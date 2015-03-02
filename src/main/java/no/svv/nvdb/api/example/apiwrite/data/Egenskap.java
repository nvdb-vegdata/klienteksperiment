package no.svv.nvdb.api.example.apiwrite.data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Egenskap {

    private String typeId;
    private List<String> verdi = new LinkedList<>();

    public Egenskap(String typeId, List<String> verdi) {
        this.typeId = typeId;
        this.verdi = verdi;
    }

    public Egenskap(String typeId, String ... verdi) {
        this.typeId = typeId;
        this.verdi.addAll(Arrays.asList(verdi));
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public List<String> getVerdi() {
        return verdi;
    }
}
