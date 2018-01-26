package fr.m2dl.infra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrchestratorTest {

	@Test
	public void createOrchestratorTest() {
		Orchestrator orchestrator = new Orchestrator();
		orchestrator.createAgent(new Agent() {

			@Override
			public void percevoir() {
				
			}
			
			@Override
			public Action decider() {
				return null;
			}
		});
		
		assertEquals(1, orchestrator.getListAgents().size());
	}
}