package fr.m2dl.infra.examples;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.Behavior;
import fr.m2dl.infra.LocalEnv;

public class Ant extends Agent {
    Boolean propertyAnt;

    public void funcAnt() {

    }
    /**
     * Default constructor
     *
     * @param b
     */
    public Ant (Behavior b) {
        super(b);
    }

    @Override
    public LocalEnv sense() {
        return null;
    }
}
