package no.svv.nvdb.api.example.apiwrite.data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Lokasjon {

    List<Punkt> punkt = new LinkedList<>();
    List<Linje> linje = new LinkedList<>();

    public Lokasjon(Punkt ... punkter) {
        this.punkt.addAll(Arrays.asList(punkter));
    }

    public Lokasjon(Linje linjer) {
        this.linje.addAll(Arrays.asList(linjer));
    }

    public List<Punkt> getPunkt() {
        return punkt;
    }

    public void setPunkt(List<Punkt> punkt) {
        this.punkt = punkt;
    }

    public List<Linje> getLinje() {
        return linje;
    }

    public void setLinje(List<Linje> linje) {
        this.linje = linje;
    }
}
