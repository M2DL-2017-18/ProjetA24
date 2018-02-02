package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.aco.domain.Pheromone;
import fr.m2dl.aco.services.IBoxable;

public class PutPheromone extends AbstractAcoAction {

    private int power;
    private int decrement;

    /**
     * @param environment
     * @param ant
     * @param power
     * @param decrement
     */
    public PutPheromone(Environment environment, Ant ant, int power, int decrement) {
        super(environment, ant);
        this.power = power;
        this.decrement = decrement;
    }

    public void act() {
        Coordinates coordinates = getAnt().getCoordinates();
        int x = coordinates.getX();
        int y = coordinates.getY();

        //On enlève la phéromone de la case s'il y en a déjà
        for (IBoxable boxable : getEnvironment().getGrid()[x][y].getBoxables()) {
            if (boxable instanceof Pheromone) {
                getEnvironment().getGrid()[x][y].removeBoxable(boxable);
            }
        }
        //Puis on rajoute la nouvelle
        getEnvironment().getGrid()[x][y].addBoxable(new Pheromone(coordinates, this.power, this.decrement));
    }
}
