package no.svv.nvdb.api.example.apiread.data;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 18.02.15
 */
public class Sok {

    private Lokasjon lokasjon;
    private List<ObjektType> objektTyper = new LinkedList<>();

    public Sok() {
    }

    public Sok(Lokasjon lokasjon, List<ObjektType> objektTyper) {
        this.lokasjon = lokasjon;
        this.objektTyper = objektTyper;
    }

    public Lokasjon getLokasjon() {
        return lokasjon;
    }

    public void setLokasjon(Lokasjon lokasjon) {
        this.lokasjon = lokasjon;
    }

    public List<ObjektType> getObjektTyper() {
        return objektTyper;
    }
}
