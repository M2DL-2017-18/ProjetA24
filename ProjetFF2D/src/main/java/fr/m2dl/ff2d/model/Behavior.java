package fr.m2dl.ff2d.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.m2dl.aco.action.PutPheromone;
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
import fr.m2dl.aco.services.IAcoAction;
import fr.m2dl.aco.services.IAcoBehavior;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.infra.IAction;
import fr.m2dl.aco.services.IAcoEnvironment;

/**
 * 
 * @author mathieukostiuk
 *
 * Implémentation du comportement d'une fourmi
 */

public class Behavior implements IAcoBehavior{
	
	
	public Optional<IBoxable> getFourmi(Box[][] grid)
	{
		
		 return grid[1][1].getBoxables().stream().filter(a -> a instanceof Ant).findFirst();
		
	}


	@Override
	public List<IAction<Ant, IAcoEnvironment>> decide(IAcoEnvironment environment) {
		List<IAction<Ant, IAcoEnvironment>> listeAction = new ArrayList<>();
		
		List<Coordinates> food = new ArrayList<>();
		food = findFoodInGrid(environment.getGrid());
		
		
		Ant ant = (Ant) getFourmi(environment.getGrid()).get();
		
		if (ant.getQuantityFoodCarrying() != 0)
			listeAction.addAll(backToNest(ant, environment.getGrid()));
		else
			if (food.size() != 0) {
				listeAction.add(directionToFood(food.get(0), ant));
				listeAction.add(new PickFood());
			}
			else {
				listeAction.add(new MoveRight());
				listeAction.add(new MoveBottom());
			}
		
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
	
	public List<IAcoAction> backToNest(Ant ant, Box[][] grid) {
		List<IAcoAction> listeActionUturn = new ArrayList<>();
		
		ant = (Ant)getFourmi(grid).get();
		
		int xAnt = ant.getCoordinates().getX();
		int yAnt = ant.getCoordinates().getY();
		
		if (xAnt > 0 && yAnt > 0) {
			listeActionUturn.add(new MoveTopLeft());
			listeActionUturn.add(new PutPheromone(xAnt, yAnt));
		}
		if (xAnt > 0 && yAnt == 0) {
			listeActionUturn.add(new MoveRight());
			listeActionUturn.add(new PutPheromone(yAnt, yAnt));
		}
		if (xAnt == 0 && yAnt > 0) {
			listeActionUturn.add(new MoveTop());
			listeActionUturn.add(new PutPheromone(yAnt, yAnt));
		}

		return listeActionUturn;
	}
	
	public void deposePheromene(int x, int y) {
		
	}
	
}
