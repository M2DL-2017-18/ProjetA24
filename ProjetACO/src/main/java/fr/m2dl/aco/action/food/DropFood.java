package fr.m2dl.aco.action.food;

import java.util.Optional;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Nest;
import fr.m2dl.aco.services.IAction;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IEnvironment;

public class DropFood implements IAction {


    public void act(Ant ant, IEnvironment env) {
        Coordinates coordinates = ant.getCoordinates();
        Optional<IBoxable> opt = env.getGrid()[coordinates.getX()][coordinates.getY()].getBoxables()
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
