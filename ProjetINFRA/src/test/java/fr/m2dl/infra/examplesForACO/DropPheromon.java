package fr.m2dl.infra.examplesForACO;

import fr.m2dl.infra.Action;
import fr.m2dl.infra.LocalEnv;

import static java.lang.Boolean.TRUE;

public class DropPheromon implements Action<Ant, LocalEnv> {

    @Override
    public void act(Ant activeAgent, LocalEnv env) {
        activeAgent.propertyAnt = TRUE;
        activeAgent.funcAnt();
    }
}