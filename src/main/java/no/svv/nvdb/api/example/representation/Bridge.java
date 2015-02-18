package no.svv.nvdb.api.example.representation;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 12.02.15
 */
public class Bridge {

    private Number objektId;
    private String name;

    private List<Line> lines = new LinkedList<>();

    public Number getObjektId() {
        return objektId;
    }

    public void setObjektId(Number objektId) {
        this.objektId = objektId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Line> getLines() {
        return lines;
    }

}
