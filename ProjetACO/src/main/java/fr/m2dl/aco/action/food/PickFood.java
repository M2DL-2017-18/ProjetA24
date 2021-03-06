package fr.m2dl.aco.action.food;

import java.util.Optional;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.services.IAcoAction;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.visitors.FoodVisitor;
import fr.m2dl.aco.visitors.IBoxableVisitor;
import fr.m2dl.aco.services.IAcoEnvironment;

public class PickFood implements IAcoAction {

    public void act(Ant ant, IAcoEnvironment env) {
    	IBoxableVisitor visitor = new FoodVisitor();
        Coordinates coordinates = ant.getCoordinates();
        Optional<IBoxable> opt = env.getGrid()[coordinates.getX()][coordinates.getY()].getBoxables()
                .stream().filter(e -> e.acceptVisitor(visitor)).findFirst();

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
