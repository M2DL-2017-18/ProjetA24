package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;

public class Nest implements IBoxable {

    private Coordinates coordinates;

    /**
     * Constructor with posisiton
     *
     * @param coordinates
     */
    public Nest(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
