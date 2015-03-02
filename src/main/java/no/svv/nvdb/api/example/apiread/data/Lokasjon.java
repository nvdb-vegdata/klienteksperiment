package no.svv.nvdb.api.example.apiread.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lokasjon {

    private String geometriUtm33;
    private String bbox;
    private Veglenker2 veglenker;

    public Lokasjon() {
    }

    public Lokasjon(String bbox) {
        this.bbox = bbox;
    }

    public String getGeometriUtm33() {
        return this.geometriUtm33;
    }

    public void setGeometriUtm33(String geometriUtm33) {
        this.geometriUtm33 = geometriUtm33;
    }

    public Veglenker2 getVeglenker() {
        return veglenker;
    }

    public void setVeglenker(Veglenker2 veglenker) {
        this.veglenker = veglenker;
    }

    public String getBbox() {
        return bbox;
    }

    public void setBbox(String bbox) {
        this.bbox = bbox;
    }
}
