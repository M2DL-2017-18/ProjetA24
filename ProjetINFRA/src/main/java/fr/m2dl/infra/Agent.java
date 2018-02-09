package fr.m2dl.infra;

import java.util.logging.Logger;
import java.util.UUID;

/**
 * Describe if the agent is dead or alive.
 * @author Infra core team
 * @since 02-02-2018
 */
enum State {
    ALIVE,
    DEAD,
}

/**
 * Describes what is an agent and what it supposed to do
 */
public abstract class Agent {
    private UUID id;
    private State state;
    private final static Logger logger = Logger.getLogger(Agent.class.getSimpleName());
    private IBehavior<Agent, IEnvironment> behavior;

    /**
     * Default constructor
     */
    public Agent(IBehavior b) {
        id = UUID.randomUUID();
        state = State.ALIVE;
        this.behavior = b;
        logger.info("Création d'un agent");
    }

    /**
     * An agent can perceive
     */
    public abstract <E extends IEnvironment> E sense(E environment);

    /**
     * An agent has a lifecycle : perceive, decide, act
     */
    protected void runLifeCycle(IEnvironment environment) {
        // sensedEnv is the maximal decision scope of the agent
        IEnvironment sensedEnv = sense(environment);
        for(IAction<Agent, IEnvironment> a : behavior.decide(sensedEnv)) {
            a.act(this, environment);
        }
    }

    /**
     * Get id
     * @return UUID the id of the agent
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Get state
     * @return State the state of the agent
     */
    public State getState() {
        return this.state;
    }

    /**
     * Set state
     * @param s the new state
     */
    public void setState(State s) {
        this.state = s;
    }

    /**
     * Set the state of agent to DEAD
     */
    public void suicide() {
        this.state = State.DEAD;
    }
}
