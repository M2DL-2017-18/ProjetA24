package fr.m2dl.ff2d.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import fr.m2dl.aco.action.PutPheromone;
import fr.m2dl.aco.action.food.DropFood;
import fr.m2dl.aco.action.move.MoveBottom;
import fr.m2dl.aco.action.move.MoveBottomLeft;
import fr.m2dl.aco.action.move.MoveBottomRight;
import fr.m2dl.aco.action.move.MoveLeft;
import fr.m2dl.aco.action.move.MoveRight;
import fr.m2dl.aco.action.move.MoveTop;
import fr.m2dl.aco.action.move.MoveTopLeft;
import fr.m2dl.aco.action.move.MoveTopRight;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.services.IAcoAction;
import fr.m2dl.aco.services.IAcoEnvironment;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.infra.IAction;

/**
 * 
 * @author mathieukostiu
 * 
 * Class defining the behavior to back to the nest
 *
 */
public class BackNest extends IBehave {
	
	private Direction dir;
	private Coordinates prec;
	private int cpt;
	private final int LIMIT = 7;
	private static final Random rand = new Random();
	private static final List<Direction> VALUES =
		    Collections.unmodifiableList(Arrays.asList(Direction.values()));

	public Optional<IBoxable> getFourmi(Box[][] grid)
	{
		
		 return grid[1][1].getBoxables().stream().filter(a -> a instanceof Ant).findFirst();
		
}
	
	@Override
	public List<IAction<Ant, IAcoEnvironment>> decide(IAcoEnvironment environment, Ant ant) {
		List<IAction<Ant, IAcoEnvironment>> listeAction = new ArrayList<>();
		
		ant = (Ant) getFourmi(environment.getGrid()).get();
		
		listeAction.addAll(backToNest(ant, environment.getGrid()));
		
		List<Coordinates> obstacles = new ArrayList<>();
		obstacles = findObstacleInGrid(environment.getGrid());
		
		
		return listeAction;
	}
	
	public List<IAcoAction> backToNest(Ant ant, Box[][] grid) {
		List<IAcoAction> listeActionUturn = new ArrayList<>();
				
		int xAnt = ant.getCoordinates().getX();
		int yAnt = ant.getCoordinates().getY();
		
		List<Coordinates> obstacles = new ArrayList<>();
		obstacles = findObstacleInGrid(grid);
		
		if (xAnt > 0 && yAnt > 0) {
			
			if (obstacles.size() != 0) {
				listeActionUturn.add(new MoveBottomLeft());
			}
			
			listeActionUturn.add(new MoveTopLeft());
			listeActionUturn.add(new PutPheromone(xAnt, yAnt));
		}
		if (xAnt > 0 && yAnt == 0) {
			if (obstacles.size() != 0) {
				listeActionUturn.add(new MoveBottomLeft());
			}
			listeActionUturn.add(new MoveLeft());
			listeActionUturn.add(new PutPheromone(xAnt, yAnt));
		}
		if (xAnt == 0 && yAnt > 0) {
			if (obstacles.size() != 0) {
				listeActionUturn.add(new MoveBottomLeft());
			}
			listeActionUturn.add(new MoveTop());
			listeActionUturn.add(new PutPheromone(xAnt, yAnt));
		}
		if (xAnt == 0 && yAnt == 0) {
			listeActionUturn.add(new DropFood());
		}

		return listeActionUturn;
	}
	
public IAcoAction move() {
		
		IAcoAction action = null;
		
		switch(dir) {
		case N:
			action = new MoveTop();
			break;
		case NE:
			action = new MoveTopRight();
			break;
		case E:
			action = new MoveRight();
			break;
		case SE:
			action = new MoveBottomRight();
			break;
		case S:
			action = new MoveBottom();
			break;
		case SW:
			action = new MoveBottomLeft();
			break;
		case W:
			action = new MoveLeft();
			break;
		case NW:
			action = new MoveTopLeft();
			break;
		}
		
		cpt++;
		
		if (cpt == LIMIT) {
			cpt = 0;
			dir = VALUES.get(rand.nextInt(VALUES.size()));
		}
		
		return action;
	}
	
	public List<Coordinates> findObstacleInGrid(Box[][] grid) {
		List<Coordinates> obstacleCoordinates = new ArrayList<Coordinates>();
		
		obstacleCoordinates = Arrays.stream(grid)
				.flatMap(row -> Arrays.stream(row).map(column -> column.getBoxables()))
				.flatMap(box -> box.stream())
				.filter(elem -> elem instanceof Obstacle)
				.map(food -> food.getCoordinates())
				.collect(Collectors.toList());
		
		return obstacleCoordinates;
	}
	
	public void checkObstacle(Ant ant) {
		if (ant.getCoordinates().getX() == prec.getX() && 
				ant.getCoordinates().getY() == prec.getY()
				)
			dir = VALUES.get(rand.nextInt(VALUES.size()));
		
	}
}
