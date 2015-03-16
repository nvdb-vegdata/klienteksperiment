package no.svv.nvdb.brobanken.representation;

/**
 * User: Sigurd Stendal
 * Date: 13.02.15
 */
public class Bbox {

    private Point northEast;
    private Point southWest;

    public Point getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Point northEast) {
        this.northEast = northEast;
    }

    public Point getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Point southWest) {
        this.southWest = southWest;
    }
}
