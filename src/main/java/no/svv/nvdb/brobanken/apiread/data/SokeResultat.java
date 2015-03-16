package no.svv.nvdb.brobanken.apiread.data;

import java.util.List;

public class SokeResultat {
    private List<Resultater> resultater;
    private SokeObjekt sokeObjekt;
    private Number totaltAntallReturnert;

    public List<Resultater> getResultater() {
        return this.resultater;
    }

    public void setResultater(List<Resultater> resultater) {
        this.resultater = resultater;
    }

    public SokeObjekt getSokeObjekt() {
        return this.sokeObjekt;
    }

    public void setSokeObjekt(SokeObjekt sokeObjekt) {
        this.sokeObjekt = sokeObjekt;
    }

    public Number getTotaltAntallReturnert() {
        return this.totaltAntallReturnert;
    }

    public void setTotaltAntallReturnert(Number totaltAntallReturnert) {
        this.totaltAntallReturnert = totaltAntallReturnert;
    }
}
