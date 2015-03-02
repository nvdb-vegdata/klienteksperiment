package no.svv.nvdb.api.example.apiwrite.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Jobb {

    private static DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

    private Oppdater oppdater;

    private String effektDato = dateformat.format(new Date());

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
