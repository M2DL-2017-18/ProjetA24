package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.Environment;
import fr.m2dl.infra.Agent;

public abstract class AbstractAcoAction {
	
	private Environment environment;
	private Agent agent;
	
	public AbstractAcoAction(Environment environment, Agent agent) {
		super();
		this.environment = environment;
		this.agent = agent;
	}

	public abstract void act();
	
	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	
}
