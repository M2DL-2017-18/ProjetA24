package fr.m2dl.aco.action.food;

import java.util.Optional;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Nest;
import fr.m2dl.aco.services.IAcoAction;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.visitors.IBoxableVisitor;
import fr.m2dl.aco.visitors.NestVisitor;
import fr.m2dl.aco.services.IAcoEnvironment;

public class DropFood implements IAcoAction {


    public void act(Ant ant, IAcoEnvironment env) {
        Coordinates coordinates = ant.getCoordinates();
        IBoxableVisitor visitor = new NestVisitor();
        Optional<IBoxable> opt = env.getGrid()[coordinates.getX()][coordinates.getY()].getBoxables()
                .stream().filter(e -> e.acceptVisitor(visitor)).findFirst();

        if(opt.isPresent()) {
            Nest nest = (Nest) opt.get();

            if(ant.getQuantityFoodCarrying() > 0) {
                nest.setQantityOfFood(nest.getQantityOfFood() + ant.getQuantityFoodCarrying());
                ant.setQuantityFoodCarrying(0);
            }
        }

    }
}
