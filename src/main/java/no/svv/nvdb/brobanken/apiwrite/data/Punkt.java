package no.svv.nvdb.brobanken.apiwrite.data;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Punkt {

    private String lenkeId;
    private String posisjon;

    public Punkt(String lenkeId, String posisjon) {
        this.lenkeId = lenkeId;
        this.posisjon = posisjon;
    }

    public String getLenkeId() {
        return lenkeId;
    }

    public void setLenkeId(String lenkeId) {
        this.lenkeId = lenkeId;
    }

    public String getPosisjon() {
        return posisjon;
    }

    public void setPosisjon(String posisjon) {
        this.posisjon = posisjon;
    }
}
