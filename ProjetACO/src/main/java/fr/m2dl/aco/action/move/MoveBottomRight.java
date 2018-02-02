package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;

public class MoveBottomRight extends AbstractMove{

	public MoveBottomRight(Environment environment, Ant ant) {
		super(environment, ant);
	}

	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX() +1 , origin.getY() +1);
	}

}
