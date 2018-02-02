package fr.m2dl.aco.action.move;

import fr.m2dl.aco.action.AbstractAcoAction;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.aco.services.IEnvironment;

public abstract class AbstractMove extends AbstractAcoAction{

	public AbstractMove(IEnvironment environment, Ant ant) {
		super(environment, ant);
	}

	public abstract Coordinates getDestination(Coordinates origin);
	
	@Override
	public void act() {
		Ant ant = getAnt();
		Coordinates origin = ant.getCoordinates();
		Coordinates destination = getDestination(origin);
		destination = stayInGrid(destination);
		if(verify(destination)){
			int x_origine = origin.getX();
			int y_origine = origin.getY();
			int x_dest = destination.getX();
			int y_dest = destination.getY();
			Box[][] grid = getEnvironment().getGrid();
			grid[x_origine][y_origine].removeBoxable(ant);
			grid[x_dest][y_dest].addBoxable(ant);
			ant.setCoordinates(destination);
		}
	}
	
	private Coordinates stayInGrid(Coordinates coordinates){
		int row = getEnvironment().getGrid().length;
		int col = getEnvironment().getGrid()[0].length;
		return new Coordinates(coordinates.getX() % row, coordinates.getY()%col);
	}
	
	private boolean verify(Coordinates coordinates){
		return true;
	}
}
