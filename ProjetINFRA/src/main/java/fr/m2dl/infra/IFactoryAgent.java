package fr.m2dl.infra;

import fr.m2dl.infra.Agent;

/**
 * Define an factory to produce agent.
 *
 * @author Infra core team
 * @since 15-02-2018
 */
public interface IFactoryAgent<A extends Agent> {
    A create();
}