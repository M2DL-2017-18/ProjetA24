package fr.m2dl.aco.domain;

import fr.m2dl.infra.IAction;
import fr.m2dl.infra.Agent;

import java.util.logging.Logger;

import fr.m2dl.aco.services.IBehavior;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IEnvironment;

public class Ant extends Agent implements IBoxable{

    private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());

    /**
     * Coordonnées de la fourmi
     */
    private Coordinates coordinates = new Coordinates(0, 0);

    /**
     * Quantité maximale que la fourmi peut transporter
     */
    private int quantityFoodMax;

    /**
     * Quantité de nourriture transportée par la fourmi
     */
    private int quantityFoodCarrying;

    public Ant(IBehavior behavior) {
        super(behavior);
        this.quantityFoodMax = 1;
        logger.info("je suis une fourmi.");
    }


    /**
     * Constructeur avec parametres : quantité de nourriture max transportable / il faudra ajouter le comportement
     * @param qtityFoodMax quantité maximum de nourriture que la fourmi peut porter
     */
    public Ant(int qtityFoodMax, IBehavior behavior) {
        super(behavior);
        this.quantityFoodMax = qtityFoodMax;
        logger.info("je suis une fourmi.");
    }

    public IEnvironment sense() {
        //TODO : add sense for the ant
    	return  null;
    }

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

    public int getQuantityFoodMax() {
        return quantityFoodMax;
    }

    public void setQuantityFoodMax(int quantityFoodMax) {
        this.quantityFoodMax = quantityFoodMax;
    }

    public int getQuantityFoodCarrying() {
        return quantityFoodCarrying;
    }

    public IAction decide() {
        return null;
    }

    public void setQuantityFoodCarrying(int quantityFoodCarrying) {
        this.quantityFoodCarrying = quantityFoodCarrying;
    }
}
