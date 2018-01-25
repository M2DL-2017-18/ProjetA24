package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;

public class Orchestrator {
    List<ActiveEntity> activeEntityList;
    List<PassiveEntity> passiveEntityList;
    List<Agent> agentList;

    Orchestrator() {
        agentList = new ArrayList<Agent>();
    }

    public void createAgent(Agent a) {
        agentList.add(a);
    }

    public void run() {
        for (Agent a : agentList) {
            a.runLifeCycle();
        }
    }
}
