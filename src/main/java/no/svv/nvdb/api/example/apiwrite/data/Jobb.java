package no.svv.nvdb.api.example.apiwrite.data;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Jobb {

    private Oppdater oppdater;

    private String effektDato = "2013-10-29";

    private String datakatalogversjon = "2.01";

    public Oppdater getOppdater() {
        return oppdater;
    }

    public void setOppdater(Oppdater oppdater) {
        this.oppdater = oppdater;
    }

    public String getEffektDato() {
        return effektDato;
    }

    public void setEffektDato(String effektDato) {
        this.effektDato = effektDato;
    }

    public String getDatakatalogversjon() {
        return datakatalogversjon;
    }

    public void setDatakatalogversjon(String datakatalogversjon) {
        this.datakatalogversjon = datakatalogversjon;
    }
}
