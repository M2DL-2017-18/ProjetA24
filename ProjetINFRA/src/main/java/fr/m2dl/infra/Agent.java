package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.UUID;

enum State {
    Live,
    Die,
}

/**
 * Describes what is an agent and what it supposed to do
 */
public abstract class Agent {
    private UUID id;
    private State state;
    private List<Action> actionList;
    private final static Logger logger = Logger.getLogger(Agent.class.getSimpleName());
    private Behavior behavior;

    /**
     * Default constructor
     */
    public Agent(Behavior b) {
        id = UUID.randomUUID();
        state = State.Live;
        actionList = new ArrayList<Action>();
        this.behavior = b;
        logger.info("Création d'un agent");
    }

    /**
     * An agent can perceive
     */
    public abstract LocalEnv sense();

    /**
     * An agent has a lifecycle : perceive, decide, act
     */
    protected void runLifeCycle() {
        LocalEnv env = sense();
        for(Action a : behavior.decide(env)) {
            a.act(this, env);
        }
    }

    public UUID getId() {
        return this.id;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State s) {
        this.state = s;
    }

    public void suicide() {
        this.state = State.Die;
    }
}
