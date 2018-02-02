package fr.m2dl.aco.action.move;

import java.util.Optional;

import fr.m2dl.aco.action.AbstractAcoAction;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IEnvironment;

/**
 * Classe qui abstrait les action de deplacement d'une fourmi.
 * 
 *
 */
public abstract class AbstractMove extends AbstractAcoAction{

	/**
	 * Constructeur d'un mouvement
	 * 
	 * @param environment pour initialiser l'attribut environnement.
	 * @param ant pour initialiser l'attribut ant
	 */
	public AbstractMove(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}

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
	 */
	@Override
	public void act() {
		Ant ant = getAnt();
		IEnvironment env = getEnvironment();
		Coordinates origin = ant.getCoordinates();
		Coordinates destination = getDestination(origin);
		destination = stayInGrid(destination);
		if(verify(destination)){
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
	 * Normalise les coordonnées pour qui'il reste dans la grille
	 * @param coordinates les coordonnées à normaliser
	 * @return les coordonnées normalisés
	 */
	private Coordinates stayInGrid(Coordinates coordinates){
		int row = getEnvironment().getGrid().length;
		int col = getEnvironment().getGrid()[0].length;
		int x = coordinates.getX() % row;
		if(x < 0) x += row;
		int y = coordinates.getY() % col;
		if(y < 0) y += col; 
		return new Coordinates(x,y);
	}
	
	/**
	 * Verifie qu'un deplacement est possible, s'il y a un obstacle a la destination le deplacment
	 * est impossible, sinon il est posssible
	 * @param coordinates la destination
	 * @return true si le deplacememt est possible,false sinon
	 */
	private boolean verify(Coordinates coordinates){
		Box box_destination = getEnvironment().getGrid()[coordinates.getX()][coordinates.getY()];
		Optional<IBoxable> opt = box_destination.getBoxables().stream().filter(b -> b instanceof Obstacle).findAny();
		return ! opt.isPresent();
	}
}
