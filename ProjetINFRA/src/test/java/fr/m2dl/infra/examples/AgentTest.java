package fr.m2dl.infra.examples;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.IBehavior;
import fr.m2dl.infra.IEnvironment;

public class AgentTest extends Agent {

    /**
     * Default constructor
     *
     * @param b
     */
    public AgentTest(IBehavior b) {
        super(b);
    }

    @Override
    public IEnvironment sense() {
        return null;
    }
}
