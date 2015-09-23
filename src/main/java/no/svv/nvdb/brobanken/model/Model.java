package no.svv.nvdb.brobanken.model;

import no.svv.nvdb.brobanken.representation.ChangeSet;

import java.util.Optional;

/**
 * A model with a singleton with the changeset that is currently running on the API Write server.
 * <p/>
 * This is an extremely simplistic approach. You would normally support more than one running changeset and store this in a database.
 * <p/>
 * User: Sigurd Stendal
 * Date: 17.02.15
 */
public class Model {

    private static Optional<ChangeSet> currentChangeSet = Optional.empty();

    public static void setChangeSet(ChangeSet changeSet) {
        currentChangeSet = Optional.ofNullable(changeSet);
    }

    public static ChangeSet getChangeSet() {
        return currentChangeSet.get();
    }

    public static boolean hasChangeSet() {
        return currentChangeSet.isPresent();
    }

}
