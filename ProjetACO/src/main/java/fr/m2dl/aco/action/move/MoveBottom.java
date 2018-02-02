package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IEnvironment;

/**
 * Deplacement en bas.
 * 
 *
 */
public class MoveBottom extends AbstractMove{

	/**
	 * Constructeur d'un mouvement vers le bas
	 * 
	 * @param environment pour initialiser l'attribut environnement.
	 * @param ant pour initialiser l'attribut ant
	 */
	public MoveBottom(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}
	
	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX(), origin.getY() + 1);
	}

}
