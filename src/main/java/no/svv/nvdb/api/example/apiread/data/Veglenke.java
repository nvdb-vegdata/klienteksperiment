package no.svv.nvdb.api.example.apiread.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Veglenke {

    private Number id;
    private Number til;
    private Number fra;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getTil() {
        return til;
    }

    public void setTil(Number til) {
        this.til = til;
    }

    public Number getFra() {
        return fra;
    }

    public void setFra(Number fra) {
        this.fra = fra;
    }
}
