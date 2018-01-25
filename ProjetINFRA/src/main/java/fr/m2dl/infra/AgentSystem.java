package fr.m2dl.infra;

import fr.m2dl.infra.environment.Environment;
import fr.m2dl.infra.factory.AgentFactoriesRegistry;
import fr.m2dl.infra.factory.FactoryAgent;
import fr.m2dl.infra.utils.OptionalConsumer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AgentSystem<T> {

    private final static Logger logger = Logger.getLogger(AgentSystem.class.getSimpleName());

    private List<Agent> agents;
    private Environment environment;
    private AgentFactoriesRegistry<T> registry;

    AgentSystem() {
        this.agents = new ArrayList<>();
        this.environment = new Environment();
        this.registry = new AgentFactoriesRegistry<>();
    }

    public void addSpawner(T token, FactoryAgent factory) {
        this.registry.addNewFactory(token, factory);
    }

    public void spawns(T token, long count) {
        OptionalConsumer.of(this.registry.createSwarm(token, count))
            .ifPresent(s -> this.agents.addAll(s))
            .ifNotPresent(() -> logger.info("Registry not found"+ token));
    }

    public void spawn(T token) {
        OptionalConsumer.of(this.registry.createAgent(token))
            .ifPresent(a -> this.agents.add(a))
            .ifNotPresent(() -> logger.info("Registry not found for "+ token));
    }

    public void run() {
        this.agents.stream()
            .filter(a -> a.getInnerState().equals(State.ALIVE))
            .forEach(a -> a.update(this.environment));
    }
}