package fr.m2dl.ff2d.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.m2dl.aco.action.PutPheromone;
import fr.m2dl.aco.action.move.MoveLeft;
import fr.m2dl.aco.action.move.MoveRight;
import fr.m2dl.aco.action.move.MoveTop;
import fr.m2dl.aco.action.move.MoveTopLeft;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
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

	public Optional<IBoxable> getFourmi(Box[][] grid)
	{
		
		 return grid[1][1].getBoxables().stream().filter(a -> a instanceof Ant).findFirst();
		
}
	
	@Override
	public List<IAction<Ant, IAcoEnvironment>> decide(IAcoEnvironment environment, Ant ant) {
		List<IAction<Ant, IAcoEnvironment>> listeAction = new ArrayList<>();

		ant = (Ant) getFourmi(environment.getGrid()).get();
		
		listeAction.addAll(backToNest(ant, environment.getGrid()));
		
		return listeAction;
	}
	
	public List<IAcoAction> backToNest(Ant ant, Box[][] grid) {
		List<IAcoAction> listeActionUturn = new ArrayList<>();
				
		int xAnt = ant.getCoordinates().getX();
		int yAnt = ant.getCoordinates().getY();
		
		if (xAnt > 0 && yAnt > 0) {
			listeActionUturn.add(new MoveTopLeft());
			listeActionUturn.add(new PutPheromone(xAnt, yAnt));
		}
		if (xAnt > 0 && yAnt == 0) {
			listeActionUturn.add(new MoveLeft());
			listeActionUturn.add(new PutPheromone(xAnt, yAnt));
		}
		if (xAnt == 0 && yAnt > 0) {
			listeActionUturn.add(new MoveTop());
			listeActionUturn.add(new PutPheromone(xAnt, yAnt));
		}

		return listeActionUturn;
	}

}
