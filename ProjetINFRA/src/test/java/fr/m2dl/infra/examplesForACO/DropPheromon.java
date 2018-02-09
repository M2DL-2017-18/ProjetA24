package fr.m2dl.infra.examplesForACO;

import fr.m2dl.infra.IAction;
import fr.m2dl.infra.IEnvironment;

import static java.lang.Boolean.TRUE;

public class DropPheromon implements IAction<Ant, IEnvironment> {

    @Override
    public void act(Ant activeAgent, IEnvironment env) {
        activeAgent.propertyAnt = TRUE;
        activeAgent.funcAnt();
    }
}