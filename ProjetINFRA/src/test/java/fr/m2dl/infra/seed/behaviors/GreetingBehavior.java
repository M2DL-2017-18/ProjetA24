package fr.m2dl.infra.seed.behaviors;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.behavior.Behavior;
import fr.m2dl.infra.environment.Environment;
import java.util.logging.Logger;

public class GreetingBehavior implements Behavior {
    final static Logger logger = Logger.getLogger(GreetingBehavior.class.getSimpleName());

    @Override
    public void run(Agent agent, Environment environment) {
        logger.info("Hello my id is" + agent.getId());
    }
}
