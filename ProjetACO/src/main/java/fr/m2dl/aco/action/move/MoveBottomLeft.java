package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IEnvironment;

public class MoveBottomLeft extends AbstractMove{

	public MoveBottomLeft(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}

	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX()-1, origin.getY() + 1);
	}

}
