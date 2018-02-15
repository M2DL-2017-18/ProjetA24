package fr.m2dl.ff2d.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import fr.m2dl.aco.action.move.MoveBottom;
import fr.m2dl.aco.action.move.MoveLeft;
import fr.m2dl.aco.action.move.MoveRight;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.services.IAction;
import fr.m2dl.aco.services.IBehavior;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IEnvironment;
import fr.m2dl.infra.Action;

/**
 * 
 * @author mathieukostiuk
 *
 * Implémentation du comportement d'une fourmi
 */

public class Behavior implements IBehavior{
	
	
	
	
	public List<IAction> uTurn() {
		List<IAction> listeActionUturn = new ArrayList<IAction>();
		
		listeActionUturn.add(new MoveRight());
		//listeActionUturn.add(new MoveBottom());
		//listeActionUturn.add(new MoveLeft());
		
		return listeActionUturn;
	}
	
	public Optional<IBoxable> getFourmi(IEnvironment environment)
	{
		
		 return environment.getGrid()[1][1].getBoxables().stream().filter(a -> a instanceof Ant).findFirst();
		
	}


	@Override
	public List<Action<Ant, IEnvironment>> decide(IEnvironment environment) {
		List<Action<Ant, IEnvironment>> listeAction = new ArrayList<>();
		
		List<Coordinates> food = new ArrayList<>();
		food = findFoodInGrid(environment.getGrid());
		
		
		Ant ant;
		
		
		
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
	
	public IAction direction(Coordinates cr) {
		return null;
	}
	
}
