package fr.m2dl.infra.seed.behaviors;

import fr.m2dl.infra.behavior.Behavior;
import fr.m2dl.infra.environment.Environment;
import fr.m2dl.infra.seed.agents.CarAgent;

public class MoveBehavior implements Behavior<CarAgent> {
    @Override
    public void run(CarAgent agent, Environment environment) {
        agent.x += 10;
        agent.y += 10;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
