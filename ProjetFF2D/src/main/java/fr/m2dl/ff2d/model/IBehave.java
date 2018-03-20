package fr.m2dl.ff2d.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.services.IAcoEnvironment;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.infra.IAction;

public abstract class IBehave {
	
	public Optional<IBoxable> getFourmi(Box[][] grid)
	{
		
		 return grid[1][1].getBoxables().stream().filter(a -> a instanceof Ant).findFirst();
		
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
	
	public abstract List<IAction<Ant, IAcoEnvironment>> decide(IAcoEnvironment environment, Ant ant);
}
