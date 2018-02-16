package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.infra.PassiveEntity;

public class Obstacle extends PassiveEntity implements IBoxable {

    private Coordinates coordinates;

    /**
     * Constructor with posisiton
     *
     * @param coordinates
     */
    public Obstacle(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
