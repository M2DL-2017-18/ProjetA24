package fr.m2dl.infra.examples;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.Behavior;
import fr.m2dl.infra.LocalEnv;

public class AgentTest extends Agent {

    /**
     * Default constructor
     *
     * @param b
     */
    public AgentTest(Behavior b) {
        super(b);
    }

    @Override
    public LocalEnv sense() {
        return null;
    }
}
