package fr.m2dl.infra.behavior;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.environment.Environment;

public interface Behavior<A extends Agent> {
    void run(A agent, Environment environment);

    default int getPriority() {
        // Push the behavior at the back of the list
        return 0;
    }
}