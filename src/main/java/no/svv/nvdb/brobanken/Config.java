package no.svv.nvdb.brobanken;

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
        if (System.getProperty("UTV") != null) {
            instance.put("url.les", "https://www.vegvesen.no/nvdb/api");
            instance.put("url.skriv", "https://www.utv.vegvesen.no/nvdb/apiskriv");
            instance.put("url.loginserver", "https://www.utv.vegvesen.no/openam/UI/Login");
        } else if (System.getProperty("POC") != null) {
            instance.put("url.les", "http://svvunvdbpoc10.vegvesen.no:1337/nvdb-rest-api");
            instance.put("url.skriv", "http://svvunvdbpoc09.vegvesen.no:9090/nvdb/apiskriv");
            instance.put("url.loginserver", "https://www.utv.vegvesen.no/openam/UI/Login");
        } else {
            instance.put("url.les", "https://www.vegvesen.no/nvdb/api");
            instance.put("url.skriv", "http://localhost:8080/nvdb/apiskriv");
            instance.put("url.loginserver", "https://www.utv.vegvesen.no/openam/UI/Login");
        }

    }

}
