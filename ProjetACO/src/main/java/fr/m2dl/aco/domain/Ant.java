package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.infra.Action;
import fr.m2dl.infra.Agent;

import java.util.logging.Logger;

public class Ant extends Agent implements IBoxable{

    private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());

    public Ant() {
        super();
        logger.info("je suis une fourmi.");
    }

    public void sense() {

    }

    public Action decide() {
        return null;
    }
}
