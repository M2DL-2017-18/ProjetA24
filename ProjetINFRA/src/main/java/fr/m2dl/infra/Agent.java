package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;

public abstract class Agent {
    private List<Action> listActions;

    public Agent() {
        listActions = new ArrayList<Action>();
    }

    public abstract void percevoir();
    public abstract Action decider();

    protected void runLifeCycle() {
        percevoir();
        Action a = decider();
        a.agir();
    }
}
