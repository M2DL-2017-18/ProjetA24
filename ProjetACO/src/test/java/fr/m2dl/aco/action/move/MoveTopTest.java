package fr.m2dl.aco.action.move;


import org.junit.BeforeClass;
import org.junit.Test;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;

public class MoveTopTest {
	
	static Environment env;
	static Ant ant;
	Coordinates obstacle_coordinates;
	Coordinates ant_coordinates;
	
	
	@BeforeClass
	public static void init(){
		env = new Environment();
		env.setGrid(new Box[3][3]);
		ant = new Ant();
	}

	@Test
	public void test(){
		ant_coordinates = new Coordinates(1, 0);
		ant.setCoordinates(ant_coordinates);
		env.getGrid()[ant_coordinates.getX()][ant_coordinates.getY()].addBoxable(ant);
		MoveTop mov = new MoveTop(env, ant);
		mov.act();
		
	}
}
