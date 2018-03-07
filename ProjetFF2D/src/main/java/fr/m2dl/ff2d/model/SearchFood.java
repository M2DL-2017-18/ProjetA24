package fr.m2dl.ff2d.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.m2dl.aco.action.food.PickFood;
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
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.domain.Pheromone;
import fr.m2dl.aco.services.IAcoAction;
import fr.m2dl.aco.services.IAcoEnvironment;
import fr.m2dl.infra.IAction;

/**
 * 
 * @author mathieukostiuk
 *
 *	Class defining the searching for food
 */
public class SearchFood extends IBehave {
	
	private Direction dir;
	private Coordinates prec;
	
	public SearchFood() {
		super();
		dir = Direction.SW;
		prec = new Coordinates(0,0);
	}
	
	@Override
	public List<IAction<Ant, IAcoEnvironment>> decide(IAcoEnvironment environment, Ant ant) {
		
		List<IAction<Ant, IAcoEnvironment>> listeAction = new ArrayList<>();
		
		List<Coordinates> food = new ArrayList<>();
		food = findFoodInGrid(environment.getGrid());

		if (food.size() != 0) {
			listeAction.add(directionToFood(food.get(0), ant));
			listeAction.add(new PickFood());
		}
		else {
			List<Coordinates> pheromone = new ArrayList<>();
			pheromone = findPheromoneInGrid(environment.getGrid());
			
			if (pheromone.size() !=0) {
				Coordinates dest = pheromone.get(0);
				for (Coordinates c : pheromone) {
					if (c.getX() != prec.getX() && c.getY() != prec.getY())
						dest = c;
				}
				listeAction.add(directionToFood(dest, ant));
			}
			else {
				List<Coordinates> obstacles = new ArrayList<>();
				obstacles = findObstacleInGrid(environment.getGrid());
				if (prec.getX() != 0 && prec.getY() != 0 && obstacles.size() != 0)
					checkObstacle(ant);
				listeAction.add(move());
			}
		}
		
		prec = ant.getCoordinates();
		
		return listeAction;
	}
	
public List<Coordinates> findFoodInGrid(Box[][] grid) {
		
		List<Coordinates> foodCoordinates = new ArrayList<Coordinates>();
		
		foodCoordinates = Arrays.stream(grid)
				.flatMap(row -> Arrays.stream(row).map(column -> column.getBoxables()))
				.flatMap(box -> box.stream())
				.filter(elem -> elem instanceof Food)
				.map(food -> food.getCoordinates())
				.collect(Collectors.toList());
		
		return foodCoordinates;
	}

public List<Coordinates> findPheromoneInGrid(Box[][] grid) {
	List<Coordinates> pheromoneCoordinates = new ArrayList<Coordinates>();
	
	pheromoneCoordinates = Arrays.stream(grid)
			.flatMap(row -> Arrays.stream(row).map(column -> column.getBoxables()))
			.flatMap(box -> box.stream())
			.filter(elem -> elem instanceof Pheromone)
			.map(food -> food.getCoordinates())
			.collect(Collectors.toList());
	
	return pheromoneCoordinates;
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
	
	public IAcoAction directionToFood(Coordinates cr, Ant ant) {
		
		int x = ant.getCoordinates().getX();
		int y = ant.getCoordinates().getY();
		x = x- cr.getX();
		y = y - cr.getY();
		
		if (x > 0) {
			if (y > 0)
				return new MoveTopLeft();
			else if (y < 0)
				return new MoveBottomLeft();
			else
				return new MoveLeft();
		}
		if (x < 0) {
			if (y > 0)
				return new MoveTopRight();
			else if (y < 0)
				return new MoveBottomRight();
			else
				return new MoveRight();
		}
		if (x == 0) {
			if (y > 0)
				return new MoveTop();
			else
				return new MoveBottom();
		}
		
		
		return null;
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
		return action;
	}
	
	public void checkObstacle(Ant ant) {
		if (ant.getCoordinates().getX() == prec.getX() && 
				ant.getCoordinates().getY() == prec.getY()
				)
			switch (dir) {
			case N:
					dir = Direction.NE;
				break;
			case NE:
					dir = Direction.E;
				break;
			case E:
					dir = Direction.SE;
				break;
			case SE:
					dir = Direction.S;
				break;
			case S:
					dir = Direction.SW;
				break;
			case SW:
					dir = Direction.W;
				break;
			case W:
					dir = Direction.NW;
				break;
			case NW:
					dir = Direction.N;
				break;
			}
		
	}
	
}
