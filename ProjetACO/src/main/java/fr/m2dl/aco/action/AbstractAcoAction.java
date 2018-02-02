package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.aco.services.IEnvironment;

public abstract class AbstractAcoAction {
	
	private IEnvironment environment;
	private Ant ant;
	
	
	public AbstractAcoAction(IEnvironment environment, Ant ant) {
		super();
		this.environment = environment;
		this.ant = ant;
	}

	public abstract void act();
	
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
