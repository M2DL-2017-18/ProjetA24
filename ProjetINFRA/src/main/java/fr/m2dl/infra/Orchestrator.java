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
    public Orchestrator() {
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

            if (this.agentIsAlive(a)) {
                this.garbageAgent(a);
            }
        }
    }

    public List<Agent> getListAgents() {
		return agentList;
    }

    /**
     * Remove the agent from the agents list in the system.
     */
    private void garbageAgent(Agent agent) {
        this.activeEntityList.remove(agent);
    }

    /**
     * Verify the agent is alive.
     */
    private boolean agentIsAlive(Agent agent) {
        return agent.getState().equals(State.Die);
    }
}
