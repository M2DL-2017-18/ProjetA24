package fr.m2dl.aco.action.food;

import java.util.Optional;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.services.IAction;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.services.IAcoEnvironment;

public class PickFood implements IAction {

    public void act(Ant ant, IAcoEnvironment env) {
        Coordinates coordinates = ant.getCoordinates();
        Optional<IBoxable> opt = env.getGrid()[coordinates.getX()][coordinates.getY()].getBoxables()
                .stream().filter(e -> e instanceof Food).findFirst();

        if(opt.isPresent()) {
            Food food = (Food) opt.get();

            if(food.getQuantity() > ant.getQuantityFoodMax()) {
                food.setQuantity(food.getQuantity() - ant.getQuantityFoodMax());
                ant.setQuantityFoodCarrying(ant.getQuantityFoodMax());
            } else {
                ant.setQuantityFoodCarrying(food.getQuantity());
                food.setQuantity(0);
                env.getGrid()[coordinates.getX()][coordinates.getY()].getBoxables().remove(food);
            }
        }

    }
}
