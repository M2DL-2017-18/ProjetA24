package fr.m2dl.infra.examplesForACO;

import fr.m2dl.infra.IFactoryAgent;

public class FactoryForAnt implements IFactoryAgent<Ant> {

    @Override
    public Ant create() {
        return new Ant(null);
    }
}