package fr.m2dl.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import fr.m2dl.infra.examples.AgentTest;
import fr.m2dl.infra.examples.FactoryForAgentTest;
import fr.m2dl.infra.examplesForACO.Ant;
import fr.m2dl.infra.examplesForACO.FactoryForAnt;

import org.junit.Before;
import org.junit.Test;

public class AgentFactoriesRegistryTest {

    AgentFactoriesRegistry factoriesRegistry;

    @Before
    public void setUp() {
        factoriesRegistry = new AgentFactoriesRegistry();
    }

    @Test
    public void itShouldRegisterDifferentFactories () {
        FactoryForAgentTest factoOfAgentTest = new FactoryForAgentTest();
        FactoryForAnt factoOfAnt = new FactoryForAnt();

        factoriesRegistry.registerAgentFactory(factoOfAgentTest);
        factoriesRegistry.registerAgentFactory(factoOfAnt);

        assertEquals(2, factoriesRegistry.getNbOfFactories());
    }

    @Test
    public void itShouldRegisterSameFactoriesType() {
        FactoryForAgentTest facto1 = new FactoryForAgentTest();
        FactoryForAgentTest facto2 = new FactoryForAgentTest();
        FactoryForAgentTest facto3 = new FactoryForAgentTest();

        factoriesRegistry.registerAgentFactory(facto1);
        factoriesRegistry.registerAgentFactory(facto2);
        factoriesRegistry.registerAgentFactory(facto3);

        assertEquals(3, factoriesRegistry.getNbOfFactories());
    }

    @Test
    public void itShouldCreateDifferentAgentByUsingDifferentFactory () {
        FactoryForAgentTest factoOfAgentTest = new FactoryForAgentTest();
        FactoryForAnt factoOfAnt = new FactoryForAnt();
        int tokenAgentTest, tokenAnt;

        tokenAgentTest = factoriesRegistry.registerAgentFactory(factoOfAgentTest);
        tokenAnt = factoriesRegistry.registerAgentFactory(factoOfAnt);

        AgentTest agentTest = (AgentTest) factoriesRegistry.createAgent(tokenAgentTest).get();
        Ant ant = (Ant) factoriesRegistry.createAgent(tokenAnt).get();

        assertTrue(agentTest instanceof AgentTest);
        assertTrue(ant instanceof Ant);
    }

    @Test
    public void itShouldCreateAnAgentWhenTheFactoryExist () {
        FactoryForAgentTest facto = new FactoryForAgentTest();

        int tokenForFacto = factoriesRegistry.registerAgentFactory(facto);

        assertTrue(factoriesRegistry.createAgent(tokenForFacto).isPresent());
    }

    @Test
    public void itShouldCreateASwarmWhenTheFactoryExist () {
        FactoryForAgentTest facto = new FactoryForAgentTest();
        int numberOfAgentToSpawn = 10;
        int tokenForFacto;
        
        tokenForFacto = factoriesRegistry.registerAgentFactory(facto);

        factoriesRegistry.createSwarm(tokenForFacto, numberOfAgentToSpawn)
            .ifPresent(agents -> {
                assertEquals(numberOfAgentToSpawn, agents.size());
                assertTrue(agents.get(0) instanceof AgentTest);
            });
    }

    @Test
    public void itShouldCreateNotCreateAnAgentWhenFactoryIsNotRegister () {
        int dumbToken = 125;

        assertFalse(factoriesRegistry.createAgent(dumbToken).isPresent());
    }

    @Test
    public void itShouldCreateNotCreateASwarmWhenFactoryIsNotRegister () {
        int dumbToken = 125;

        assertFalse(factoriesRegistry.createSwarm(dumbToken, 3).isPresent());
    }

}