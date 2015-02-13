package no.svv.nvdb.api.example.apiread.data;

public class VegObjekter {
    private Lokasjon lokasjon;
    private Number objektId;
    private Number objektTypeId;
    private String navn;

    public Lokasjon getLokasjon() {
        return this.lokasjon;
    }

    public void setLokasjon(Lokasjon lokasjon) {
        this.lokasjon = lokasjon;
    }

    public Number getObjektId() {
        return this.objektId;
    }

    public void setObjektId(Number objektId) {
        this.objektId = objektId;
    }

    public Number getObjektTypeId() {
        return this.objektTypeId;
    }

    public void setObjektTypeId(Number objektTypeId) {
        this.objektTypeId = objektTypeId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
