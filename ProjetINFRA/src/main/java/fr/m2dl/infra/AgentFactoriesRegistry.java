package fr.m2dl.infra;

import fr.m2dl.infra.IFactoryAgent;
import fr.m2dl.infra.Agent;
import java.util.*;

/**
 * Define a registry of abstract factory which produce agents.
 *
 * @author Infra core team
 * @since 15-02-2018
 */
public class AgentFactoriesRegistry {
    private Map<Integer, IFactoryAgent<Agent>> factories;
    private int indexFactory;

    /**
    * Default constructor
    */
    public AgentFactoriesRegistry() {
        this.factories = new HashMap<>();
        this.indexFactory = 0;
    }

    /**
    * Create an agent by using a factory registred
    * @param token Id of the factory use to create the agent.
    * @return get None if the factory doesn't exist
    */
    public Optional<Agent> createAgent(int token) {
        return getFactoryImpl(token)
                .map(IFactoryAgent::create);
    }

    /**
    * Create <b>count</b> agents by using a factory registred.
    * @param token Id of the factory use to create the agent.
    * @return get None if the factory doesn't exist
    */
    public Optional<List<Agent>> createSwarm(int token, long count) {
        return getFactoryImpl(token)
                .map(f -> createAgents(f, count));
    }

    /**
    * Register a new factory.
    * @param factory a factory than can create Agent.
    * @return Token use to retrieve a factory.
    */
    public int registerAgentFactory(IFactoryAgent factory) {
        int token = this.indexFactory;
        this.factories.put(token, factory);
        this.indexFactory += 1;

        return token;
    }

    /**
    * Get the number of factory registred.
    * @return number of factory
    */
    public int getNbOfFactories() {
        return this.factories.size();
    }

    /**
    * Create <b>count</b> agents.
    * @param factory the factory use to create the agents.
    * @param factory the number of agent to create.
    * @return List of agents create.
    */
    private List<Agent> createAgents(IFactoryAgent factory, long count) {
        ArrayList<Agent> agents = new ArrayList<>();

        for (int i = 0; i < count; i++)
            agents.add(factory.create());

        return agents;
    }

    /**
    * Get the number of factory registred.
    * @param token Use this token to retrieve the factory.
    * @return The factory, get None if the factory doesn't exist
    */
    private Optional<IFactoryAgent> getFactoryImpl(int token) {
        IFactoryAgent factory = factories.get(token);

        if (factory != null)
            return Optional.of(factory);
        else
            return Optional.empty();
    }
}