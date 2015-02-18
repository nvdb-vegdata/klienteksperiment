package no.svv.nvdb.api.example.apiwrite.data;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Egenskap {

    private String typeId;
    private String verdi;

    public Egenskap(String typeId, String verdi) {
        this.typeId = typeId;
        this.verdi = verdi;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getVerdi() {
        return verdi;
    }

    public void setVerdi(String verdi) {
        this.verdi = verdi;
    }
}
