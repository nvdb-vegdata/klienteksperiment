package no.svv.nvdb.brobanken.apiread.data;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 18.02.15
 */
public class ObjektType {

    private Integer id;
    private Long antall;
    private List<String> filter = new LinkedList<>();

    public ObjektType() {
    }

    public ObjektType(Integer id, Long antall) {
        this.id = id;
        this.antall = antall;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAntall() {
        return antall;
    }

    public void setAntall(Long antall) {
        this.antall = antall;
    }

    public List<String> getFilter() {
        return filter;
    }
}
