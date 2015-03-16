package no.svv.nvdb.brobanken.apiwrite.data;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Linje {

    private Number lenkeId;
    private Number fra;
    private Number til;

    public Linje(Number lenkeId, Number fra, Number til) {
        this.lenkeId = lenkeId;
        this.fra = fra;
        this.til = til;
    }

    public Number getLenkeId() {
        return lenkeId;
    }

    public void setLenkeId(Number lenkeId) {
        this.lenkeId = lenkeId;
    }

    public Number getFra() {
        return fra;
    }

    public void setFra(Number fra) {
        this.fra = fra;
    }

    public Number getTil() {
        return til;
    }

    public void setTil(Number til) {
        this.til = til;
    }
}
