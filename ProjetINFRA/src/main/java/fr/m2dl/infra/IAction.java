package fr.m2dl.infra;

/**
 * Describes an action that an agent can make
 *
 * @author Infra core team
 * @since 02-02-2018
 */
public interface IAction<A extends Agent, E extends IEnvironment> {

    /**
     * The concrete action
     * @param activeAgent the agent that perform this action
     * @param environment the environment of the active agent
     */
    void act(A activeAgent, E environment);
}
