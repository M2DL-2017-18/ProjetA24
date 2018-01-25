package fr.m2dl.infra;

import fr.m2dl.infra.seed.factories.SimpleAgentFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AgentSystemTest {

    @Test
    @Tag("example")
    void example_of_greeting_agent() {
        // Create all your factories
        UUID safUuid = UUID.randomUUID();
        SimpleAgentFactory saf = new SimpleAgentFactory();

        // Register them in your system
        AgentSystem system = new AgentSystem();
        system.addSpawner(safUuid, saf);

        // Seed agents
        system.spawns(safUuid, 3);
        system.spawn(safUuid);

        // Run the simulation
        system.run();
    }
}