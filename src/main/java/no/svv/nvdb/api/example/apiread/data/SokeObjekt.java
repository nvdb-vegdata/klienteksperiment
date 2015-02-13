package no.svv.nvdb.api.example.apiread.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class SokeObjekt {

    @JsonIgnore
    private Lokasjon lokasjon;
    private List<ObjektTyper> objektTyper;

    public Lokasjon getLokasjon() {
        return this.lokasjon;
    }

    public void setLokasjon(Lokasjon lokasjon) {
        this.lokasjon = lokasjon;
    }

    public List<ObjektTyper> getObjektTyper() {
        return this.objektTyper;
    }

    public void setObjektTyper(List<ObjektTyper> objektTyper) {
        this.objektTyper = objektTyper;
    }
}
