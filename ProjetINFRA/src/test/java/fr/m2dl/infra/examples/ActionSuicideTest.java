package fr.m2dl.infra.examples;

import fr.m2dl.infra.IAction;
import fr.m2dl.infra.Agent;
import fr.m2dl.infra.IEnvironment;

public class ActionSuicideTest implements IAction<Agent, IEnvironment> {

    @Override
    public void act(Agent activeAgent, IEnvironment env) {
        activeAgent.suicide();
    }
}
