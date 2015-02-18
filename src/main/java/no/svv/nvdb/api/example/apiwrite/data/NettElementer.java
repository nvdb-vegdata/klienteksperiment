package no.svv.nvdb.api.example.apiwrite.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NettElementer {

    Punkt punkt = null;
    Linje linje = null;

    public NettElementer() {
    }

    public NettElementer(Punkt punkt) {
        this.punkt = punkt;
    }

    public NettElementer(Linje linje) {
        this.linje = linje;
    }

    public Punkt getPunkt() {
        return punkt;
    }

    public void setPunkt(Punkt punkt) {
        this.punkt = punkt;
    }

    public Linje getLinje() {
        return linje;
    }

    public void setLinje(Linje linje) {
        this.linje = linje;
    }
}
