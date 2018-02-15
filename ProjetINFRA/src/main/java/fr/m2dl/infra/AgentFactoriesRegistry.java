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

    public AgentFactoriesRegistry() {
        this.factories = new HashMap<>();
        this.indexFactory = 0;
    }

    public Optional<Agent> createAgent(int token) {
        return getFactoryImpl(token)
                .map(IFactoryAgent::create);
    }

    public Optional<List<Agent>> createSwarm(int token, long count) {
        return getFactoryImpl(token)
                .map(f -> createAgents(f, count));
    }

    public int registerAgentFactory(IFactoryAgent factory) {
        int token = this.indexFactory;
        this.factories.put(token, factory);
        this.indexFactory += 1;

        return token;
    }

    public int getNbOfFactories() {
        return this.factories.size();
    }

    private List<Agent> createAgents(IFactoryAgent factory, long count) {
        ArrayList<Agent> agents = new ArrayList<>();

        for (int i = 0; i < count; i++)
            agents.add(factory.create());

        return agents;
    }

    private Optional<IFactoryAgent> getFactoryImpl(int token) {
        IFactoryAgent factory = factories.get(token);

        if (factory != null)
            return Optional.of(factory);
        else
            return Optional.empty();
    }
}