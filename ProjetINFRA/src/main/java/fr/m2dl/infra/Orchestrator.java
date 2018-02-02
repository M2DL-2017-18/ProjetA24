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
        List<Agent> agentsToGarbage = new ArrayList<Agent>();

        for (Agent a : agentList) {
            a.runLifeCycle();

            if (this.agentIsDead(a)) {
                // we check if the agent is dead after the lifecycle 
                // because his state can only change during his lifecycle (INFRA-FN13)
                agentsToGarbage.add(a);
            }
        }

        this.garbageAgents(agentsToGarbage);
    }

    public List<Agent> getListAgents() {
		return agentList;
    }

    /**
     * Remove the agents with a Die state from the agents list in the system.
     * @param a the agent to remove
     */
    private void garbageAgents(List<Agent> agents) {
        this.agentList.removeAll(agents);
    }

    /**
     * Verify if the agent is dead.
     * @param a the agent to verify
     */
    private boolean agentIsDead(Agent agent) {
        return agent.getState().equals(State.Die);
    }
}
