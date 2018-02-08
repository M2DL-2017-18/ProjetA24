package fr.m2dl.infra;

/**
 * Describes an action that an agent can make
 *
 * @author Infra core team
 * @since 02-02-2018
 */
public interface Action<A extends Agent, L extends LocalEnv> {

    /**
     * The concrete action
     * @param activeAgent the agent that perform this action
     * @param env the local environment
     */
    void act(A activeAgent, L env);
}
