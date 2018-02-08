package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Coordinates;


/**
 * Deplacement vers le haut à gauche.
 * 
 */
public class MoveTopLeft extends AbstractMove {


	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX() - 1, origin.getY() - 1);
	}
}
