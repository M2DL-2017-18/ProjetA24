package fr.m2dl.aco.domain;

import fr.m2dl.infra.IAction;
import fr.m2dl.infra.Agent;

import java.util.logging.Logger;

public class Ant extends Agent {

    private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());

    public Ant() {
        super();
        logger.info("je suis une fourmi.");
    }

    public void sense() {

    }

    public IAction decide() {
        return null;
    }
}
