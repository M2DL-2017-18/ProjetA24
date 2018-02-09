package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.infra.PassiveEntity;

public class Nest extends PassiveEntity implements IBoxable {

    /**
     * Coordonées du nid
     */
    private Coordinates coordinates;

    /**
     * Quantité de nourriture rapportée par les fourmis
     */
    private int qantityOfFood;

    /**
     * Constructeur avec position
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

    public int getQantityOfFood() {
        return qantityOfFood;
    }

    public void setQantityOfFood(int qantityOfFood) {
        this.qantityOfFood = qantityOfFood;
    }
}
