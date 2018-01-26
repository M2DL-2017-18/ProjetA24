package fr.m2dl.infra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class OrchestratorTest {
	Orchestrator orchestrator;

	@Before
	public void init() {
		orchestrator = new Orchestrator();
	}

	public void generateAgent(Orchestrator orchestrator) {
		orchestrator.createAgent(new Agent() {

			@Override
			public void sense() {
				
			}
			
			@Override
			public Action decide() {
				return null;
			}
		});
	}

	@Test
	public void createOrchestratorWithAgentTest() {
		generateAgent(orchestrator);
		
		assertEquals(1, orchestrator.getListAgents().size());
	}
}