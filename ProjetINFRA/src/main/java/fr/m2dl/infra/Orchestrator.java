package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;

public class Orchestrator {
    List<ActiveEntity> listActiveEntities;
    List<PassiveEntity> listPassiveEntities;
    List<Agent> listAgents;


    Orchestrator() {
        listAgents = new ArrayList<Agent>();
    }

    public void createAgent(Agent a) {
        listAgents.add(a);
    }

    public void run() {
        for (Agent a : listAgents) {
            a.runLifeCycle();
        }
    }
}
