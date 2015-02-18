package no.svv.nvdb.api.example;

import java.util.LinkedList;
import java.util.List;

/**
 * A singleton with all job id's that is currently running on the API Write server and that we are interested in.
 * <p/>
 * User: Sigurd Stendal
 * Date: 17.02.15
 */
public class JobRegister {

    public static List<String> instance = new LinkedList<>();

}
