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
		List<Action> l = new ArrayList<Action>();

		AgentTest a = new AgentTest(new Behavior() {
			@Override
			public List<Action> decide(LocalEnv env) {
				l.add(new Action() {
					@Override
					public void act(Agent myAgent, LocalEnv env) {
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
		a.runLifeCycle();
		assertTrue(testPassedByActFunction);
	}


	public void generateSuicideAgent(Orchestrator orchestrator) {
		Agent a = new Agent(new Behavior() {
			@Override
			public List<Action> decide(LocalEnv env) {
				List<Action> l = new ArrayList<Action>();
				l.add(new ActionSuicideTest());
				return l;
			}
		}) {
			@Override
			public LocalEnv sense() {
				return null;
			}
		};
	}

	@Test
	public void createOrchestratorWithSuicideAgentTest() {
		// Given an orchestrator with an Agent that suicide immediately.
		generateSuicideAgent(orchestrator);

		// When we run the system
		orchestrator.run();

		// Then the agent should be removed from the system.
		assertEquals(0, orchestrator.getListAgents().size());
	}
}