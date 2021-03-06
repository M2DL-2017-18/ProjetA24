package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.visitors.IBoxableVisitor;
import fr.m2dl.infra.ActiveEntity;
import fr.m2dl.infra.IEnvironment;

public class Pheromone extends ActiveEntity implements IBoxable {

    private int power;
    private int decrement;
    private Coordinates coordinates;

    /**
     * Constructor with posisiton
     *
     * @param coordinates
     * @param power     The remaining power of the pheromone
     * @param decrement The value of the decrementation when update (power = power - decrement)
     */
    public Pheromone(Coordinates coordinates, int power, int decrement) {
        this.coordinates = coordinates;
        this.power = power;
        this.decrement = decrement;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
	public boolean acceptVisitor(IBoxableVisitor visitor) {
		return visitor.verify(this);
	}

    
	@Override
	public void runLifeCycle(IEnvironment env) {
		// TODO Auto-generated method stub
		
	}
}
