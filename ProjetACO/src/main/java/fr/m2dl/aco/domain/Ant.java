package fr.m2dl.aco.domain;

import fr.m2dl.infra.IAction;
import fr.m2dl.infra.IEnvironment;
import fr.m2dl.infra.Agent;

import java.util.logging.Logger;

import fr.m2dl.aco.services.IAcoBehavior;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IAcoEnvironment;
import fr.m2dl.aco.util.Util;

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
     * Coordonn�es du nid
     */
    
    private Coordinates nestCoordinates = new Coordinates(0, 0) ;

	/**
     * Quantité de nourriture transportée par la fourmi
     */
    private int quantityFoodCarrying;

    public Ant(IAcoBehavior behavior) {
        super(behavior);
        this.quantityFoodMax = 1;
        logger.info("je suis une fourmi.");
    }


    /**
     * Constructeur avec parametres : quantité de nourriture max transportable / il faudra ajouter le comportement
     * @param qtityFoodMax quantité maximum de nourriture que la fourmi peut porter
     */
    public Ant(int qtityFoodMax, IAcoBehavior behavior) {
        super(behavior);
        this.quantityFoodMax = qtityFoodMax;
        logger.info("je suis une fourmi.");
    }

    @Override
    public AcoEnvironment sense(IEnvironment env) {
    	AcoEnvironment acoEnvironment = (AcoEnvironment) env;
        AcoEnvironment envToReturn = new AcoEnvironment(3,3);
        Box[][] grid = new Box[3][3];
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Coordinates coord = Util.stayInGrid(acoEnvironment, new Coordinates(coordinates.getX()+i, coordinates.getY()+j) );
                grid[i+1][j+1] = acoEnvironment.getGrid()[coord.getX()][coord.getY()];
            }
        }
        envToReturn.setGrid(grid);
    	return  envToReturn;
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


    public Coordinates getNestCoordinates() {
		return nestCoordinates;
	}


	public void setNestCoordinates(Coordinates nestCoordinates) {
		this.nestCoordinates = nestCoordinates;
	}
}
