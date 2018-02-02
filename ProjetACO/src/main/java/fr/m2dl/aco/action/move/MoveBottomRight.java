package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IEnvironment;

/**
 * Deplacement en bas a droite.
 * 
 *
 */
public class MoveBottomRight extends AbstractMove{

	/**
	 * Constructeur d'un mouvement vers le bas a droite
	 * 
	 * @param environment pour initialiser l'attribut environnement.
	 * @param ant pour initialiser l'attribut ant
	 */
	public MoveBottomRight(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}

	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX() +1 , origin.getY() +1);
	}

}
