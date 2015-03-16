package no.svv.nvdb.brobanken.apiread.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lokasjon {

    private String geometriUtm33;
    private String bbox;
    private Veglenker veglenker;

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

    public Veglenker getVeglenker() {
        return veglenker;
    }

    public void setVeglenker(Veglenker veglenker) {
        this.veglenker = veglenker;
    }

    public String getBbox() {
        return bbox;
    }

    public void setBbox(String bbox) {
        this.bbox = bbox;
    }
}
