package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;

/**
 * Scheduler of the Multi-Agent System
 *
 * It "runs" all the agents in the system
 */
public class Orchestrator {
    List<ActiveEntity> activeEntityList;
    List<PassiveEntity> passiveEntityList;
    List<Agent> agentList;

    /**
     * Default constructor
     */
    Orchestrator() {
        agentList = new ArrayList<Agent>();
    }

    /**
     * Adds an agent to the system
     * @param a the agent to add
     */
    public void createAgent(Agent a) {
        agentList.add(a);
    }

    /**
     * Runs all the agent lifecycle sequentially
     */
    public void run() {
        for (Agent a : agentList) {
            a.runLifeCycle();
        }
    }
}
