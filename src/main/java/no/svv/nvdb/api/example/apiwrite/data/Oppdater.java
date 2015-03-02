package no.svv.nvdb.api.example.apiwrite.data;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Oppdater {

    List<VegObjekt2> vegObjekter = new LinkedList<>();

    public List<VegObjekt2> getVegObjekter() {
        return vegObjekter;
    }

    public void setVegObjekter(List<VegObjekt2> vegObjekter) {
        this.vegObjekter = vegObjekter;
    }
}
