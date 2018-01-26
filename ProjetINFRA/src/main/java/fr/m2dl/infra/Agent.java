package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Describes what is an agent and what it supposed to do
 */
public abstract class Agent {
    private List<Action> actionList;
    private final static Logger logger = Logger.getLogger(Agent.class.getSimpleName());

    /**
     * Default constructor
     */
    public Agent() {
        actionList = new ArrayList<Action>();
        logger.info("Création d'un agent");
    }

    /**
     * An agent can perceive
     */
    public abstract void sense();

    /**
     * An agent can decide what will be it's next action
     * @return the next action to do
     */
    public abstract Action decide();

    /**
     * An agent has a lifecycle : perceive, decide, act
     */
    protected void runLifeCycle() {
        sense();
        Action a = decide();
        a.act();
    }
}
