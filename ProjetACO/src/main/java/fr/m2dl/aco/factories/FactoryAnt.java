package fr.m2dl.aco.factories;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.services.IAcoBehavior;
import fr.m2dl.infra.IFactoryAgent;

public class FactoryAnt implements IFactoryAgent<Ant> {

    private IAcoBehavior behavior;

    public void setBehavior(IAcoBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public Ant create() {
        return new Ant(behavior);
    }
}
