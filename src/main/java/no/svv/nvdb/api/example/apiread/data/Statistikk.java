package no.svv.nvdb.api.example.apiread.data;

public class Statistikk {
    private Number antallFunnet;
    private Number antallReturnert;
    private Number returnertStrekningslengde;
    private Number totalStrekningslengde;

    public Number getAntallFunnet() {
        return this.antallFunnet;
    }

    public void setAntallFunnet(Number antallFunnet) {
        this.antallFunnet = antallFunnet;
    }

    public Number getAntallReturnert() {
        return this.antallReturnert;
    }

    public void setAntallReturnert(Number antallReturnert) {
        this.antallReturnert = antallReturnert;
    }

    public Number getReturnertStrekningslengde() {
        return this.returnertStrekningslengde;
    }

    public void setReturnertStrekningslengde(Number returnertStrekningslengde) {
        this.returnertStrekningslengde = returnertStrekningslengde;
    }

    public Number getTotalStrekningslengde() {
        return this.totalStrekningslengde;
    }

    public void setTotalStrekningslengde(Number totalStrekningslengde) {
        this.totalStrekningslengde = totalStrekningslengde;
    }
}
