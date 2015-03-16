package no.svv.nvdb.brobanken.apiread.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Egenskap {

    private String id;
    private String navn;
    private String verdi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getVerdi() {
        return verdi;
    }

    public void setVerdi(String verdi) {
        this.verdi = verdi;
    }
}
