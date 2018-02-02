package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.infra.Agent;

public abstract class AbstractAcoAction {
	
	private Environment environment;
	private Ant ant;
	
	
	public AbstractAcoAction(Environment environment, Ant ant) {
		super();
		this.environment = environment;
		this.ant = ant;
	}

	public abstract void act();
	
	public Environment getEnvironment() {
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
