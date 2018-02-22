package fr.m2dl.aco.visitors;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.domain.Nest;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.domain.Pheromone;

public class FoodVisitor implements IBoxableVisitor{

	@Override
	public boolean verify(Ant a) {
		return false;
	}

	@Override
	public boolean verify(Obstacle o) {
		return false;
	}

	@Override
	public boolean verify(Pheromone p) {
		return false;
	}

	@Override
	public boolean verify(Nest n) {
		return false;
	}

	@Override
	public boolean verify(Food f) {
		return true;
	}

}
