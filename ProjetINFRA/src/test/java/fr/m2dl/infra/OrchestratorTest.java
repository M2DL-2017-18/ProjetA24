package fr.m2dl.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import fr.m2dl.infra.examples.ActionSuicideTest;
import fr.m2dl.infra.examples.AgentTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrchestratorTest {
	Orchestrator orchestrator;
	Boolean testPassedByActFunction = false;

	@Before
	public void init() {
		orchestrator = new Orchestrator();
	}

	private Agent generateAgent(Orchestrator orchestrator) {
		final List<IAction<AgentTest, IEnvironment>> l = new ArrayList();

		AgentTest a = new AgentTest(new IBehavior<AgentTest, IEnvironment>() {
			public List<IAction<AgentTest, IEnvironment>> decide(AgentTest a, IEnvironment env) {
				l.add(new IAction<AgentTest, IEnvironment>() {
					public void act(AgentTest activeAgent, IEnvironment environment) {
						testPassedByActFunction = true;
					}
				});
				return l;
			}
		});

		orchestrator.createAgent(a);
		return a;
	}



	@Test
	public void testCreateOrchestratorWithAgentTest() {
		generateAgent(orchestrator);
		
		assertEquals(1, orchestrator.getListAgents().size());
	}

	@Test
	public void testRunAgentLifeCycle() {
		Agent a = generateAgent(orchestrator);
		a.runLifeCycle(new IEnvironment() { });
		assertTrue(testPassedByActFunction);
	}


	public void generateSuicideAgent(Orchestrator orchestrator) {
		Agent a = new Agent(new IBehavior() {
			public List<IAction> decide(Agent a, IEnvironment env) {
				List<IAction> l = new ArrayList<IAction>();
				l.add(new ActionSuicideTest());
				return l;
			}
		}) {
			@Override
			public IEnvironment sense(IEnvironment environment) {
				return null;
			}
		};
	}

	@Test
	public void createOrchestratorWithSuicideAgentTest() {
		// Given an orchestrator with an Agent that suicide immediately.
		generateSuicideAgent(orchestrator);

		// When we run the system
		orchestrator.run(new IEnvironment() {
			@Override
			public String toString() {
				return "Environment";
			}
		});

		// Then the agent should be removed from the system.
		assertEquals(0, orchestrator.getListAgents().size());
	}
}