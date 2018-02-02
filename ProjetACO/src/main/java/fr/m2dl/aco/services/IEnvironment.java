package fr.m2dl.aco.services;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Environment;

/**
 * Created by hichem on 02/02/2018.
 */
public interface IEnvironment {
    void createAnts(int number ,Behavior behavior);
    void createFood(Coordinates coordinates);
    void createObstacle(Coordinates coordinates);
    Box[][] getGrid();
}
