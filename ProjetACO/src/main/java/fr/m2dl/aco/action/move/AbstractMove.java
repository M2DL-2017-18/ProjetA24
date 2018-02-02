package fr.m2dl.aco.action.move;

import fr.m2dl.aco.action.AbstractAcoAction;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;

public abstract class AbstractMove extends AbstractAcoAction{

	public AbstractMove(Environment environment, Ant ant) {
		super(environment, ant);
	}

	public abstract Coordinates getDestination(Coordinates origin);
	
	@Override
	public void act() {
		Coordinates origin = new Coordinates(0, 0);
		Coordinates destination = getDestination(origin);
		if(verify()){
			//int x_origine =
			//int y_origine =
			int x_dest = destination.getX();
			int y_dest = destination.getY();
			//environnement[origine][] .remove(ant)
			//environnement[destination][].add(ant)
			//ant.coordinate = destination
		}
	}
	
	private boolean verify(){
		return false;
	}
}
