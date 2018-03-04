package fr.m2dl.aco.visitors;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.domain.Nest;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.domain.Pheromone;

public interface IBoxableVisitor {
	boolean verify(Ant a);
	boolean verify(Obstacle o);
	boolean verify(Pheromone p);
	boolean verify(Nest n);
	boolean verify(Food f);
	
}
