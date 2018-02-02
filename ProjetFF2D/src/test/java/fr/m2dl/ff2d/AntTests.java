package fr.m2dl.ff2d;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.m2dl.aco.domain.Ant;

public class AntTests {

	private Ant ant = null;
	
	
	@Test
	public final void testCreationAntNotNull() {
		this.ant = new Ant();
		assertNotNull(ant);
	}
	
}
