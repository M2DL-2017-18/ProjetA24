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
 * @author mathieukostiu
 *
 * Implémentation du comportement d'une fourmi
 */

public class Behavior implements IAcoBehavior{
	
	private Ant ant;
	private IBehave state;
	private Boolean wasSearching = false;
	
	public void setAnt(Ant a) {
		ant = a;
	}

	@Override
	public List<IAction<Ant, IAcoEnvironment>> decide(IAcoEnvironment environment) {
		List<IAction<Ant, IAcoEnvironment>> listeAction = new ArrayList<>();
		
			
		if (ant.getQuantityFoodCarrying() != 0) {
			if (wasSearching)
				state = new BackNest();
			listeAction.addAll(state.decide(environment, ant));
			wasSearching = false;
		}
		else {
			if (!wasSearching)
				state = new SearchFood();
			listeAction.addAll(state.decide(environment, ant));
			wasSearching = true;
		}
		
		return listeAction;
	}
	
}