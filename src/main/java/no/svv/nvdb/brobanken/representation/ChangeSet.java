package no.svv.nvdb.brobanken.representation;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 17.02.15
 */
public class ChangeSet {

    private String id;
    private String status;
    private List<Bridge> bridges = new LinkedList<>();

    public ChangeSet(List<Bridge> bridges) {
        this(null, null, bridges);
    }

    public ChangeSet(String id, String status, List<Bridge> bridges) {
        this.id = id;
        this.status = status;
        this.bridges = bridges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }
}
