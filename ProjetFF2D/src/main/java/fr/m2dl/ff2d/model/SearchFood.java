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
	
}
