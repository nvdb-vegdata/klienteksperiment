package no.svv.nvdb.api.example.apiwrite.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * User: Sigurd Stendal
 * Date: 17.02.15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    private Date mottatt;
    private String fremdrift;

    public Date getMottatt() {
        return mottatt;
    }

    public void setMottatt(Date mottatt) {
        this.mottatt = mottatt;
    }

    public String getFremdrift() {
        return fremdrift;
    }

    public void setFremdrift(String fremdrift) {
        this.fremdrift = fremdrift;
    }
}
