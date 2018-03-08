package fr.m2dl.infra.examples;

import fr.m2dl.infra.IAction;
import fr.m2dl.infra.IBehavior;
import fr.m2dl.infra.IEnvironment;

import java.util.ArrayList;
import java.util.List;

public class BehaviorTest implements IBehavior {
    @Override
    public List<IAction> decide(IEnvironment env) {
        List<IAction> l = new ArrayList<>();
        l.add(new ActionSuicideTest());
        return l;
    }
}
