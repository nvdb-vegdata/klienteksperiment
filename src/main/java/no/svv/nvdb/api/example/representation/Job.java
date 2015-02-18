package no.svv.nvdb.api.example.representation;

/**
 * User: Sigurd Stendal
 * Date: 17.02.15
 */
public class Job {

    private String id;
    private String status;

    public Job(String id, String status) {
        this.id = id;
        this.status = status;
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
}
