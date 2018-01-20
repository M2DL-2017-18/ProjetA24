package fr.m2dl.infra.factory;

import fr.m2dl.infra.Agent;
import java.util.*;

public class AgentFactoriesRegistry<Token> {
    private Map<Token, FactoryAgent> factories;

    public AgentFactoriesRegistry() {
        this.factories = new HashMap<>();
    }

    public Optional<Agent> createAgent(Token token) {
        return getFactoryImpl(token)
                .map(FactoryAgent::create);
    }

    public Optional<List<Agent>> createSwarm(Token token, long count) {
        return getFactoryImpl(token)
                .map(f -> createAgents(f, count));
    }

    public void addNewFactory(Token token, FactoryAgent factory) {
        this.factories.put(token, factory);
    }

    private List<Agent> createAgents(FactoryAgent factory, long count) {
        ArrayList<Agent> agents = new ArrayList<>();

        for (int i = 0; i < count; i++)
            agents.add(factory.create());

        return agents;
    }

    private Optional<FactoryAgent> getFactoryImpl(Token token) {
        FactoryAgent factory = factories.get(token);

        if (factory != null)
            return Optional.of(factory);
        else
            return Optional.empty();
    }
}
