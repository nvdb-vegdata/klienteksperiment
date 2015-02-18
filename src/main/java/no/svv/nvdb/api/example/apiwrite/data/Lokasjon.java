package no.svv.nvdb.api.example.apiwrite.data;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Lokasjon {

    private NettElementer nettElementer;

    public Lokasjon() {
    }

    public Lokasjon(NettElementer nettElementer) {
        this.nettElementer = nettElementer;
    }

    public NettElementer getNettElementer() {
        return nettElementer;
    }

    public void setNettElementer(NettElementer nettElementer) {
        this.nettElementer = nettElementer;
    }
}
