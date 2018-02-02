package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.aco.services.IEnvironment;

/**
 * classe qui abstrait toutes les actions dans ACO. 
 * 
 *
 */
public abstract class AbstractAcoAction implements IAction{
	
	/**
	 * l'environnement dans lequel se deroule l'action
	 */
	private IEnvironment environment;
	/**
	 * La fourmi qui effectue l'action
	 */
	private Ant ant;

	/**
	 * Constructeur d'une action.
	 * 
	 * @param environment pour initialiser l'attribut environnement.
	 * @param ant pour initialiser l'attribut ant
	 */
	public AbstractAcoAction(IEnvironment environment, Ant ant) {
		super();
		this.environment = environment;
		this.ant = ant;
	}

	
	public IEnvironment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Ant getAnt() {
		return ant;
	}

	public void setAnt(Ant ant) {
		this.ant = ant;
	}
	
	
}
