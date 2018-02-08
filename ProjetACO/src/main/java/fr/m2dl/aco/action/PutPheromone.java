package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Pheromone;
import fr.m2dl.aco.services.IAction;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IEnvironment;

public class PutPheromone implements IAction {

    private int power;
    private int decrement;

    /**
     * @param environment
     * @param ant
     * @param power
     * @param decrement
     */
    public PutPheromone(int power, int decrement) {
        super();
        this.power = power;
        this.decrement = decrement;
    }

    public void act(Ant ant, IEnvironment env) {
        Coordinates coordinates = ant.getCoordinates();
        int x = coordinates.getX();
        int y = coordinates.getY();
        //On enlève la phéromone de la case s'il y en a déjà
        for (IBoxable boxable : env.getGrid()[x][y].getBoxables()) {
            if (boxable instanceof Pheromone) {
                env.getGrid()[x][y].removeBoxable(boxable);
            }
        }
        //Puis on rajoute la nouvelle
        env.getGrid()[x][y].addBoxable(new Pheromone(coordinates, this.power, this.decrement));
    }

}