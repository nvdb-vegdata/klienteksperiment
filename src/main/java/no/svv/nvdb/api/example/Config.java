package no.svv.nvdb.api.example;

import java.util.HashMap;

/**
 * Global configuration based on environment.
 * <p/>
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Config extends HashMap<String, String> {

    public static final Config instance = new Config();

    static {
        if (System.getProperty("POC") != null) {
            instance.put("url.les", "http://svvunvdbpoc09.vegvesen.no:1337/nvdb-rest-api");
            instance.put("url.skriv", "http://svvunvdbpoc09.vegvesen.no:9090/nvdb/apiskriv");
        } else {
            instance.put("url.les", "https://www.vegvesen.no/nvdb/api");
            instance.put("url.skriv", "http://localhost:8080/nvdb/apiskriv");
        }

    }

}
