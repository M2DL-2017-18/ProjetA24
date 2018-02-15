package fr.m2dl.ff2d.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.services.IBehavior;
import fr.m2dl.aco.services.IEnvironment;
import fr.m2dl.ff2d.model.Behavior;

public class BehaviorTest {

	private Box[][] grid;
	private Behavior b;
	
	@Before
	public void createGrid() {
		b = new Behavior();
		grid = new Box[3][3];
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				grid[i][j] = new Box();
	}
	
	@Test
	public void testFindFoodCoordinatesListNotNull() {
		assertNotNull(b.findFoodInGrid(grid));
	}
	
	@Test
	public void testFindOneFood() {
		Food f = new Food(new Coordinates(1,1),1);
		grid[2][2].addBoxable(f);
		assertEquals(b.findFoodInGrid(grid).size(),1);
	}
	
	@Test
	public void testBackToNest() {
		Ant ant = new Ant(b);
		Coordinates coord = new Coordinates(2, 3);
		ant.setCoordinates(coord);
		
		assertNotNull(b.backToNest(ant, grid));
	}
	
	@Test
	public void testThreeFoods() {
		Food f1 = new Food(new Coordinates(1,1),1);
		Food f2 = new Food(new Coordinates(0,0),1);
		Food f3 = new Food(new Coordinates(1,0),1);
		grid[2][2].addBoxable(f1);
		grid[1][2].addBoxable(f2);
		grid[0][2].addBoxable(f3);
		assertEquals(b.findFoodInGrid(grid).size(),3);
	}
	
	@Test
	public void testFoodCoordinatesX() {
		Food f1 = new Food(new Coordinates(1,2),1);
		grid[1][2].addBoxable(f1);
		assertEquals(b.findFoodInGrid(grid).get(0).getX(),1);
	}
	
	@Test
	public void testFoodCoordinatesY() {
		Food f1 = new Food(new Coordinates(1,2),1);
		grid[1][2].addBoxable(f1);
		assertEquals(b.findFoodInGrid(grid).get(0).getY(),2);
	}

}
