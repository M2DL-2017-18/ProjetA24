package fr.m2dl.infra.seed.factories;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.factory.FactoryAgent;
import fr.m2dl.infra.seed.agents.SimpleAgent;
import fr.m2dl.infra.seed.behaviors.GreetingBehavior;

public class SimpleAgentFactory implements FactoryAgent{
    @Override
    public Agent create() {
        SimpleAgent sa = new SimpleAgent();
        sa.addBehavior(new GreetingBehavior());
        return sa;
    }
}
