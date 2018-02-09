package fr.m2dl.infra.examplesForACO;

import fr.m2dl.infra.Agent;
import fr.m2dl.infra.IBehavior;
import fr.m2dl.infra.IEnvironment;

public class Ant extends Agent {
    Boolean propertyAnt;

    public void funcAnt() {

    }
    /**
     * Default constructor
     *
     * @param b
     */
    public Ant (IBehavior b) {
        super(b);
    }

    @Override
    public IEnvironment sense(IEnvironment environment) {
        return null;
    }
}
