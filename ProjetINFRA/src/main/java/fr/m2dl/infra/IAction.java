package fr.m2dl.infra;

/**
 * Describes an action that an agent can make
 *
 * @author Infra core team
 * @since 22-02-2018
 */
public interface IAction<A extends Agent, E extends IEnvironment> {

    /**
     * The concrete action
     * @param activeAgent the agent that perform this action
     * @param environment the environment of the active agent
     * @param inbox the inbox use by agent to put a message to send
     **/
    void act(A activeAgent, E environment, Inbox inbox);
}
