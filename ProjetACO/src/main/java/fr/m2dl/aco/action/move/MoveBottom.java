package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IEnvironment;

public class MoveBottom extends AbstractMove{

	public MoveBottom(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}
	
	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX(), origin.getY() + 1);
	}

}
