package fr.m2dl.infra;

/**
 * Describes an action that an agent can make
 */
public interface Action<A extends Agent> {
    /**
     * The concrete action
     */
    void act(A activeAgent, LocalEnv env);
}
