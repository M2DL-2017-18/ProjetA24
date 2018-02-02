package fr.m2dl.aco.action.move;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IEnvironment;


/**
 * Deplacement vers le haut.
 * 
 */
public class MoveTop extends AbstractMove{

	/**
	 * Constructeur d'un mouvement vers le haut
	 * 
	 * @param environment pour initialiser l'attribut environnement.
	 * @param ant pour initialiser l'attribut ant
	 */
	public MoveTop(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}

	@Override
	public Coordinates getDestination(Coordinates origin) {
		return new Coordinates(origin.getX(), origin.getY() - 1);
	}

}
