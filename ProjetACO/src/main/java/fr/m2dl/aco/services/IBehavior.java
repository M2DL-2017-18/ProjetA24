package fr.m2dl.aco.services;

import fr.m2dl.infra.Behavior;

import java.util.List;

/**
 * Created by hichem on 02/02/2018.
 */
public interface IBehavior extends Behavior {
    List<IAction> decide (IEnvironment environment);
}
