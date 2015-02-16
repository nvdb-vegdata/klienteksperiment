package no.svv.nvdb.api.example.apiread.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VegObjekter {
    private Lokasjon lokasjon;
    private Number objektId;
    private Number objektTypeId;
    private String navn;

    private List<Egenskap> egenskaper;

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

    public List<Egenskap> getEgenskaper() {
        return egenskaper;
    }

    public void setEgenskaper(List<Egenskap> egenskaper) {
        this.egenskaper = egenskaper;
    }
}
