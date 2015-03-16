package no.svv.nvdb.brobanken.apiread.data;

import java.util.Date;

/**
 * User: Sigurd Stendal
 * Date: 05.03.15
 */
public class Datakatalog {

    private String version;
    private Date date;
    private Integer id;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
