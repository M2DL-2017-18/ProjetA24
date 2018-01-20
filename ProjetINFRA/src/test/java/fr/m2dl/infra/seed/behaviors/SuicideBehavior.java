package fr.m2dl.infra.seed.behaviors;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.behavior.Behavior;
import fr.m2dl.infra.environment.Environment;
import fr.m2dl.infra.State;

public class SuicideBehavior implements Behavior {

    @Override
    public void run(Agent agent, Environment environment) {
        agent.setInnerState(State.DEAD);
    }
}