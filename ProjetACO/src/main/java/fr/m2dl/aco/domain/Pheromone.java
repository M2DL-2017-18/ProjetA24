package fr.m2dl.aco.domain;

public class Pheromone {

    private int posX;
    private int posY;
    private int power;
    private int decrement;

    /**
     * Constructor with posisiton
     *
     * @param posX  The X position
     * @param posY  The Y position
     * @param power The remaining power of the pheromone
     * @param decrement The value of the decrementation when update (power = power - decrement)
     */
    public Pheromone(int posX, int posY, int power, int decrement) {
        this.posX = posX;
        this.posY = posY;
        this.power = power;
        this.decrement = decrement;
    }
}
