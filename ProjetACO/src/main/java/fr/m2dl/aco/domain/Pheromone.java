package fr.m2dl.aco.domain;

public class Pheromone {

    private int posX;
    private int posY;
    private int power;

    /**
     * Constructor with posisiton
     *
     * @param posX  The X position
     * @param posY  The Y position
     * @param power The remaining power of the pheromone
     */
    public Pheromone(int posX, int posY, int power) {
        this.posX = posX;
        this.posY = posY;
        this.power = power;
    }
}
