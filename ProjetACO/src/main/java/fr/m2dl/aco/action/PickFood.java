package fr.m2dl.aco.action;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.services.IBoxable;

import java.util.Optional;
import java.util.stream.Stream;

public class PickFood extends AbstractAcoAction {

    public PickFood(Environment environment, Ant ant) {
        super(environment, ant);
    }

    public void act() {
        Ant ant = this.getAnt();
        Coordinates coordinates = ant.getCoordinates();
        Optional<IBoxable> opt = this.getEnvironment().getGrid()[coordinates.getX()][coordinates.getY()].getBoxables()
                .stream().filter(e -> e instanceof Food).findFirst();

        if(opt.isPresent()) {
            Food food = (Food) opt.get();

            if(food.getQuantity() > ant.getQuantityFoodMax()) {
                food.setQuantity(food.getQuantity() - ant.getQuantityFoodMax());
                ant.setQuantityFoodCarrying(ant.getQuantityFoodMax());
            } else {
                ant.setQuantityFoodCarrying(food.getQuantity());
                food.setQuantity(0);
                this.getEnvironment().getGrid()[coordinates.getX()][coordinates.getY()].getBoxables().remove(food);
            }
        }

    }
}
