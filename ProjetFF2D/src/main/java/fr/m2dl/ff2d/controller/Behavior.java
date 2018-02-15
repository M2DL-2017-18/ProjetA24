package fr.m2dl.ff2d.controller;

import java.util.ArrayList;
import java.util.List;

import fr.m2dl.aco.action.move.MoveRight;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.services.IAction;
import fr.m2dl.aco.services.IBehavior;
import fr.m2dl.aco.services.IAcoEnvironment;
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


	@Override
	public List<Action<Ant, IAcoEnvironment>> decide(IAcoEnvironment environment) {
		List<Action<Ant, IAcoEnvironment>> listeAction = new ArrayList<>();
		
		listeAction.addAll(uTurn());
		
		return listeAction;
	}



}