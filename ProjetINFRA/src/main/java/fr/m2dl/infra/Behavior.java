package fr.m2dl.infra;

import java.util.List;

/**
 * Define the behavior of an Agent.
 *
 * @author Infra core team
 * @since 02-02-2018
 */
public interface Behavior <A extends Agent, L extends LocalEnv> {

    /**
     * Pick a list of actions given the local environment.
     * @param env A view of the local environment.
     * @return A list of action to perform.
     */
    List<Action<A, L>> decide(L env);
}
