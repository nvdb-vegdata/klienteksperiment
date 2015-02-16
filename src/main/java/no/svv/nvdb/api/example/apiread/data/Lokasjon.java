package no.svv.nvdb.api.example.apiread.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lokasjon {

    private String geometriUtm33;

    public String getGeometriUtm33() {
        return this.geometriUtm33;
    }

    public void setGeometriUtm33(String geometriUtm33) {
        this.geometriUtm33 = geometriUtm33;
    }
}
