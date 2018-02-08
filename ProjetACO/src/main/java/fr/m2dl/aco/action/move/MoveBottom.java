package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Coordinates;

/**
 * Deplacement en bas.
 * 
 *
 */
public class MoveBottom extends AbstractMove{
	
	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX(), origin.getY() + 1);
	}

}
