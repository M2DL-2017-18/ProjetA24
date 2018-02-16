package fr.m2dl.aco.action.move;

import java.util.Optional;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.services.IAcoAction;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IAcoEnvironment;
import fr.m2dl.aco.util.Util;

/**
 * Classe qui abstrait les actions de deplacement d'une fourmi.
 * 
 *
 */
public abstract class AbstractMove implements IAcoAction{

	
	/**
	 * Methode pour calculer les coordonnées de destination en fonction d'un mouvement.
	 * 
	 * @param origin les coordonnées de depart de la fourmi
	 * @return les coordonnées de destination en fonction du mouvement
	 */
	public abstract Coordinates getDestination(Coordinates origin);
	
	/**
	 * Methode qui realise le deplacement.
	 * Les coordonnées de destination est calculée en fonction du deplacement puis, 
	 * puis ceux-ci sont normalisés pour gerer les debordements . Si le deplacement est posssible
	 * La fourmi est deplacée sur la destination.
	 * 
	 * @param ant la fourmi qui effectue le mouvement
	 * @param env l'environnement dans lequel se deplace la fourmi
	 */
	@Override
	public void act(Ant ant, IAcoEnvironment env) {
		Coordinates origin = ant.getCoordinates();
		Coordinates destination = getDestination(origin);
		destination = Util.stayInGrid(env,destination);
		if(verify(env,destination)){
			int x_origine = origin.getX();
			int y_origine = origin.getY();
			int x_dest = destination.getX();
			int y_dest = destination.getY();
			Box[][] grid = env.getGrid();
			grid[x_origine][y_origine].removeBoxable(ant);
			grid[x_dest][y_dest].addBoxable(ant);
			ant.setCoordinates(destination);
		}
	}
	
	/**
	 * Verifie qu'un deplacement est possible, s'il y a un obstacle a la destination le deplacment
	 * est impossible, sinon il est posssible
	 * 
	 * @param env l'environnement 
	 * @param coordinates la destination
	 * @return true si le deplacememt est possible,false sinon
	 */
	private boolean verify(IAcoEnvironment env, Coordinates coordinates){
		Box box_destination = env.getGrid()[coordinates.getX()][coordinates.getY()];
		Optional<IBoxable> opt = box_destination.getBoxables().stream().filter(b -> b instanceof Obstacle).findAny();
		return ! opt.isPresent();
	}
}
