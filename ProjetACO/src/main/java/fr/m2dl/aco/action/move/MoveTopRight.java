package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IEnvironment;

public class MoveTopRight extends AbstractMove {

	/**
	 * Constructeur d'un mouvement vers le haut a droite
	 * 
	 * @param environment pour initialiser l'attribut environnement.
	 * @param ant pour initialiser l'attribut ant
	 */
	public MoveTopRight(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}

	
	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX() + 1, origin.getY() - 1);
	}

}
