package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;

public class Food implements IBoxable {

    private int quantity;
    private Coordinates coordinates;

    /**
     * Constructor with posisiton and quantity of food
     *
     * @param coordinates
     * @param quantity    quantity of food
     */
    public Food(Coordinates coordinates, int quantity) {
        this.coordinates = coordinates;
        this.quantity = quantity;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
