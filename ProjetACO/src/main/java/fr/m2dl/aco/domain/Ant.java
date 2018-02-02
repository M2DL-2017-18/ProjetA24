package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.infra.Action;
import fr.m2dl.infra.Agent;

import java.util.logging.Logger;

public class Ant extends Agent implements IBoxable{

    private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());

    private Coordinates coordinates;
    private Integer qtityFoodMax;
    private Integer qtityFoodCarrying = 0;

    public Ant() {
        super();
        this.qtityFoodMax = 1;
        logger.info("je suis une fourmi.");
    }


    /**
     * Constructor with parameter, will need to add behavior when implemented
     * @param qtityFoodMax maximum quantity of food an ant can carry
     */
    public Ant(Integer qtityFoodMax) {
        super();
        this.qtityFoodMax = qtityFoodMax;
        logger.info("je suis une fourmi.");
    }

    public void sense() {

    }

    public Action decide() {
        return null;
    }

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

}
