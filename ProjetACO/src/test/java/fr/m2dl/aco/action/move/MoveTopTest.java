package fr.m2dl.aco.action.move;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.AcoEnvironment;
import fr.m2dl.aco.domain.Obstacle;

public class MoveTopTest {
	
	static AcoEnvironment env;
	static Ant ant;
	Coordinates obstacle_coordinates;
	Coordinates ant_coordinates;
	
	
	@Before
	public  void init(){
		env = new AcoEnvironment(3,3);
		ant = new Ant(null);
	}

	@Test
	public void testMoveNormal(){
		ant_coordinates = new Coordinates(1, 1);
		ant.setCoordinates(ant_coordinates);
		env.getGrid()[1][1].addBoxable(ant);
		MoveTop mov = new MoveTop();
		mov.act(ant,env);
		assertFalse(env.getGrid()[1][1].getBoxables().contains(ant));
		assertTrue(env.getGrid()[1][0].getBoxables().contains(ant));
	}
	
	@Test
	public void testMoveToObstacle(){
		ant_coordinates = new Coordinates(1, 1);
		ant.setCoordinates(ant_coordinates);
		env.getGrid()[1][1].addBoxable(ant);
		env.getGrid()[1][0].addBoxable(new Obstacle(new Coordinates(1, 0)));
		MoveTop mov = new MoveTop();
		mov.act(ant,env);
		assertTrue(env.getGrid()[1][1].getBoxables().contains(ant));
		assertFalse(env.getGrid()[1][0].getBoxables().contains(ant));
	}
	
	@Test
	public void testMoveTraverse(){
		ant_coordinates = new Coordinates(1, 0);
		ant.setCoordinates(ant_coordinates);
		env.getGrid()[1][0].addBoxable(ant);
		MoveTop mov = new MoveTop();
		mov.act(ant,env);
		assertFalse(env.getGrid()[1][0].getBoxables().contains(ant));
		assertTrue(env.getGrid()[1][2].getBoxables().contains(ant));
	}
}
