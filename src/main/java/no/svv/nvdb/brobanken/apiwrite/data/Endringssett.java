package no.svv.nvdb.brobanken.apiwrite.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Endringssett {

    private static DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    private Oppdater oppdater;

    private String effektDato = dateformat.format(new Date());

    private String datakatalogversjon;

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
