package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IEnvironment;

import java.util.ArrayList;
import java.util.List;

public class Environment implements IEnvironment {

	private Box[][] grid;
	private int posAntNestX;
	private int posAntNestY;

	/**
	 * Generic constructor
	 */
	public Environment(int row, int col) {
		grid = new Box[row][col];
	}

	public void createAnts(int number, Behavior behavior) {

	}

	public void createFood(Coordinates coordinates) {

	}

	public void createObstacle(Coordinates coordinates) {

	}

	public Box[][] getGrid() {
		return grid;
	}
}
