package no.svv.nvdb.brobanken.apiwrite.data;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Oppdater {

    List<VegObjekt> vegObjekter = new LinkedList<>();

    public List<VegObjekt> getVegObjekter() {
        return vegObjekter;
    }

    public void setVegObjekter(List<VegObjekt> vegObjekter) {
        this.vegObjekter = vegObjekter;
    }
}
