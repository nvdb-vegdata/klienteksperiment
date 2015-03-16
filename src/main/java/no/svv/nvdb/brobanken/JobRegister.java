package no.svv.nvdb.brobanken;

import no.svv.nvdb.brobanken.representation.Job;

import java.util.Optional;

/**
 * A singleton with all job id's that is currently running on the API Write server and that we are interested in.
 * <p/>
 * User: Sigurd Stendal
 * Date: 17.02.15
 */
public class JobRegister {

    public static Optional<Job> instance = Optional.empty();

}