package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.*;
import fr.m2dl.aco.services.IBoxable;

import java.util.Optional;

public class DropFood extends AbstractAcoAction {

    public DropFood(Environment environment, Ant ant) {
        super(environment, ant);
    }

    public void act() {
        Ant ant = this.getAnt();
        Coordinates coordinates = ant.getCoordinates();
        Optional<IBoxable> opt = this.getEnvironment().getGrid()[coordinates.getX()][coordinates.getY()].getBoxables()
                .stream().filter(e -> e instanceof Nest).findFirst();

        if(opt.isPresent()) {
            Nest nest = (Nest) opt.get();

            if(ant.getQuantityFoodCarrying() > 0) {
                nest.setQantityOfFood(nest.getQantityOfFood() + ant.getQuantityFoodCarrying());
                ant.setQuantityFoodCarrying(0);
            }
        }

    }
}
