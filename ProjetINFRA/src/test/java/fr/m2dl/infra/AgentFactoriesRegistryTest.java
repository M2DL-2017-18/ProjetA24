package fr.m2dl.infra;

import fr.m2dl.infra.factory.AgentFactoriesRegistry;
import fr.m2dl.infra.seed.agents.SimpleAgent;
import fr.m2dl.infra.seed.factories.SimpleAgentFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AgentFactoriesRegistryTest {
    private static AgentFactoriesRegistry<UUID> registry;
    private static SimpleAgentFactory simpleAgentFactory;
    private static UUID uuidOfSimpleAgentFactory;

    @BeforeAll
    static void setUp() {
        // Given a registry with a simple factory for create simple agent
        uuidOfSimpleAgentFactory = UUID.randomUUID();
        registry = new AgentFactoriesRegistry<>();
        simpleAgentFactory = new SimpleAgentFactory();
        registry.addNewFactory(uuidOfSimpleAgentFactory, simpleAgentFactory);
    }

    @Test
    void it_should_create_a_simple_agent() {
        // When we ask for a new agent
        Optional<Agent> res = registry.createAgent(uuidOfSimpleAgentFactory);

        // Then we should retrieve this agent with the factory registered
        assertTrue(res.isPresent());
    }

    @Test
    void it_should_create_a_swarm_of_simple_agent() {
        //Given a swarm size
        long sz = 3;

        // When we ask for a new swarm of simple agent with the factory registered
        Optional<List<Agent>> res = registry.createSwarm(uuidOfSimpleAgentFactory, sz);

        // Then we should retrieve this swarm
        assertTrue(res.isPresent());
        // And we should retrieve the correct size and type
        assertEquals(sz, res.get().size());
        res.get().forEach(a -> assertTrue(a instanceof SimpleAgent));
    }

    @Test
    void it_should_not_create_an_agent_when_factory_dont_exist() {
        // When we ask for a new agent with a factory not registered
        // Then we should get Optional.None
        assertFalse(registry.createAgent(UUID.randomUUID()).isPresent());
    }

    @Test
    void it_should_not_create_a_swarm_when_factory_dont_exist() {
        // When we ask for a new swarm with a factory not registered
        // Then we should get Optional.None
        assertFalse(registry.createSwarm(UUID.randomUUID(), 100).isPresent());
    }
}