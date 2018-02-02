package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IEnvironment;

public class Environment implements IEnvironment {

    private Box[][] grid;
    private Nest nest;

    /**
     * Creation de x fourmis avec le même comportement
     * Ajout de ces fourmis dans la grille à l'emplacement du nid
     *
     * @param number   nombre de fourmis à créer
     * @param behavior comportement des fourmis
     */
    public void createAnts(int number, Behavior behavior) {
        for (int i = 0; i < number; i++) {
            Ant ant = new Ant(behavior);
            grid[nest.getCoordinates().getX()][nest.getCoordinates().getY()].addBoxable(ant);
        }
    }

    /**
     * Création d'une nourriture à un emplacement donné
     *
     * @param coordinates Coordonnées de l'emplacement
     */
    public void createFood(Coordinates coordinates, int quantity) {

        Food food = new Food(coordinates, quantity);
        grid[coordinates.getX()][coordinates.getY()].addBoxable(food);

    }

    /**
     * Création d'un obstacle à un emplacement donné (que sur une case)
     *
     * @param coordinates Coordonnées de l'emplacement
     */
    public void createObstacle(Coordinates coordinates) {
        Obstacle obstacle = new Obstacle(coordinates);
        grid[coordinates.getX()][coordinates.getY()].addBoxable(obstacle);

    }

    /**
     * Getter de la grille
     *
     * @return la grille
     */
    public Box[][] getGrid() {
        return grid;
    }

	public void setGrid(Box[][] grid) {
		this.grid = grid;
	}
    
}
