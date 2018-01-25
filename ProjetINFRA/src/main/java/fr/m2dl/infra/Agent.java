package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;

public abstract class Agent {
    private List<Action> actionList;

    public Agent() {
        actionList = new ArrayList<Action>();
    }

    public abstract void sense();
    public abstract Action decide();

    protected void runLifeCycle() {
        sense();
        Action a = decide();
        a.act();
    }
}
