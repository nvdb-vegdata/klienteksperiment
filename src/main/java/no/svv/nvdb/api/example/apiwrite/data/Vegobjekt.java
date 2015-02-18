package no.svv.nvdb.api.example.apiwrite.data;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class VegObjekt {

    private Number typeId;
    private Number nvdbId;
    private String versjon;
    private Lokasjon lokasjon;

    private List<Egenskap> egenskaper = new LinkedList<>();

    public VegObjekt(Number typeId, Number nvdbId) {
        this.typeId = typeId;
        this.nvdbId = nvdbId;
    }

    public Number getTypeId() {
        return typeId;
    }

    public void setTypeId(Number typeId) {
        this.typeId = typeId;
    }

    public Number getNvdbId() {
        return nvdbId;
    }

    public void setNvdbId(Number nvdbId) {
        this.nvdbId = nvdbId;
    }

    public String getVersjon() {
        return versjon;
    }

    public void setVersjon(String versjon) {
        this.versjon = versjon;
    }

    public Lokasjon getLokasjon() {
        return lokasjon;
    }

    public void setLokasjon(Lokasjon lokasjon) {
        this.lokasjon = lokasjon;
    }

    public List<Egenskap> getEgenskaper() {
        return egenskaper;
    }

    public void setEgenskaper(List<Egenskap> egenskaper) {
        this.egenskaper = egenskaper;
    }
}
