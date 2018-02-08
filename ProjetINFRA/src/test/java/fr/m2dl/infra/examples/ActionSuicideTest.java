package fr.m2dl.infra.examples;

import fr.m2dl.infra.Action;
import fr.m2dl.infra.Agent;
import fr.m2dl.infra.LocalEnv;

public class ActionSuicideTest implements Action<Agent, LocalEnv> {

    @Override
    public void act(Agent activeAgent, LocalEnv env) {
        activeAgent.suicide();
    }
}
