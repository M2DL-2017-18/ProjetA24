package fr.m2dl.aco.domain;

public class Food {

    private int quantity;

    private int posX;
    private int posY;

    /**
     * Constructor with posisiton and quantity of food
     *
     * @param posX The X position
     * @param posY The Y position
     * @param quantity quantity of food
     */
    public Food(int posX, int posY, int quantity) {
        this.posX = posX;
        this.posY = posY;
        this.quantity = quantity;
    }
}
