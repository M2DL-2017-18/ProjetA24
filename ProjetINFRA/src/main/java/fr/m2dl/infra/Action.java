package fr.m2dl.infra;

/**
 * Describes an action that an agent can make
 */
public interface Action {
    /**
     * The concrete action
     */
    void act(Agent activeAgent, LocalEnv env);
}
