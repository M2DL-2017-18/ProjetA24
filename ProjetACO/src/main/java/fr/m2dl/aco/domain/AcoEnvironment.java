package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBehavior;
import fr.m2dl.aco.services.IAcoEnvironment;
import fr.m2dl.infra.Orchestrator;

public class AcoEnvironment implements IAcoEnvironment {

    private Box[][] grid;
    private Nest nest;

    private Orchestrator orchestrator;

    public AcoEnvironment(int row, int col) {
        this.orchestrator = new Orchestrator();
        grid = new Box[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = new Box();
            }
        }

    }


    /**
     * Création de x fourmis avec le même comportement
     * Ajout de ces fourmis dans la grille à l'emplacement du nid
     * Si le nid n'existe pas on crée un par défaut aux coordonnées (0,0)
     *
     * @param number   nombre de fourmis à créer
     * @param behavior comportement des fourmis
     */
    public void createAnts(int number, IBehavior behavior) {
        if (nest == null) {
            Coordinates positionNest = new Coordinates(0, 0);
            createNest(positionNest);
        }
        int nestX = nest.getCoordinates().getX();
        int nestY = nest.getCoordinates().getY();
        for (int i = 0; i < number; i++) {
            Ant ant = new Ant(behavior);
            grid[nestX][nestY].addBoxable(ant);
            orchestrator.createAgent(ant);
        }
    }

    /**
     * Création d'une nourriture à un emplacement donné
     *
     * @param coordinates Coordonnées de l'emplacement
     * @param quantity    La quantité de la nourriture
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
     * Création d'un nid à un emplacement donné (que sur une case)
     *
     * @param coordinates Coordonnées de l'emplacement
     */
    public void createNest(Coordinates coordinates) {

        if (nest == null) {
            nest = new Nest(coordinates);
            grid[coordinates.getX()][coordinates.getY()].addBoxable(nest);
        }

    }

    /**
     * Getter de la grille
     *
     * @return la grille
     */
    public Box[][] getGrid() {
        return grid;
    }

    /**
     * Lance l'orchestrateur
     */
    @Override
    public void run() {
        orchestrator.run();
    }

    public void setGrid(Box[][] grid) {
        this.grid = grid;
    }

}
