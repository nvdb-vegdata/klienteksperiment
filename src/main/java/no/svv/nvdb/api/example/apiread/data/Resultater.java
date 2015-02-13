package no.svv.nvdb.api.example.apiread.data;

import java.util.List;

public class Resultater {
    private Statistikk statistikk;
    private Number typeId;
    private List<VegObjekter> vegObjekter;

    public Statistikk getStatistikk() {
        return this.statistikk;
    }

    public void setStatistikk(Statistikk statistikk) {
        this.statistikk = statistikk;
    }

    public Number getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Number typeId) {
        this.typeId = typeId;
    }

    public List<VegObjekter> getVegObjekter() {
        return this.vegObjekter;
    }

    public void setVegObjekter(List<VegObjekter> vegObjekter) {
        this.vegObjekter = vegObjekter;
    }
}
