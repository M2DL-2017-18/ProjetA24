package fr.m2dl.ff2d.controller;

import java.util.List;
import java.util.ArrayList;

import fr.m2dl.ff2d.mocks.*;

/**
 * 
 * @author mathieukostiuk
 *
 * Implémentation du comportement d'une fourmi
 */
public class Behavior {
	
	/**
	 * 
	 * @param e Environnement immédiat de la fourmi
	 * @return  La liste des actions que la fourmi exécutera	selon son environnement
	 */
	public List<Action> decide(IEnvironment e) {
		List<Action> listeAction = new ArrayList<Action>();
		
		listeAction.addAll(uTurn());
		
		return listeAction;
	}
	
	public List<Action> uTurn() {
		List<Action> listeActionUturn = new ArrayList<Action>();
		
		listeActionUturn.add(Action.MOVERIGHT);
		listeActionUturn.add(Action.MOVEBOTTOM);
		listeActionUturn.add(Action.MOVELEFT);
		
		return listeActionUturn;
	}
}
