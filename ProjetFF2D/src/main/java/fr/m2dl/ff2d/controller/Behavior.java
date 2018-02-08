package fr.m2dl.ff2d.controller;

import java.util.List;

import fr.m2dl.aco.action.move.*;
import fr.m2dl.aco.services.IAction;
import fr.m2dl.aco.services.IBehavior;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IEnvironment;
import fr.m2dl.infra.Action;
import fr.m2dl.infra.LocalEnv;

import java.util.ArrayList;

/**
 * 
 * @author mathieukostiuk
 *
 * Implémentation du comportement d'une fourmi
 */
public class Behavior implements IBehavior{
	
	/**
	 * 
	 * @param e Environnement immédiat de la fourmi
	 * @return  La liste des actions que la fourmi exécutera	selon son environnement
	 */
	@Override
	public List<IAction> decide(IEnvironment e) {
		List<IAction> listeAction = new ArrayList<IAction>();
		
		listeAction.addAll(uTurn());
		
		return listeAction;
	}
	
	public List<IAction> uTurn() {
		List<IAction> listeActionUturn = new ArrayList<IAction>();
		
		listeActionUturn.add(new MoveRight());
		listeActionUturn.add(new MoveBottom());
		listeActionUturn.add(new MoveLeft());
		
		return listeActionUturn;
	}
	
	public List<IAction> exploreEnv(IEnvironment e) {
		return null;
	}

	@Override
	public List<Action> decide(LocalEnv env) {
		List<Action> listeAction = new ArrayList<Action>();
		
		listeAction.addAll(uTurn());
		
		return listeAction;
	}
}
