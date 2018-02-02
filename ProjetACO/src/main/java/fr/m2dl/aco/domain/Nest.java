package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;

public class Nest implements IBoxable{

    private int posX;
    private int posY;

    /**
     * Constructor with posisiton
     *
     * @param posX The X position
     * @param posY The Y position
     */
    public Nest(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
