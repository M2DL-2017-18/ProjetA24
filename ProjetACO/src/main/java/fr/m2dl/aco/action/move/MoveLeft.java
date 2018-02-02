package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IEnvironment;

/**
 * Deplacement a gauche.
 * 
 *
 */
public class MoveLeft extends AbstractMove{

	/**
	 * Constructeur d'un mouvement a gauche
	 * 
	 * @param environment pour initialiser l'attribut environnement.
	 * @param ant pour initialiser l'attribut ant
	 */
	public MoveLeft(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}

	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX()-1, origin.getY());
	}

}
