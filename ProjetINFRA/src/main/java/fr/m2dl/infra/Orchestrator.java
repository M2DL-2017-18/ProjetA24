package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;

/**
 * Scheduler of the Multi-Agent System
 *
 * It "runs" all the agents in the system
 * @author Infra core team
 * @since 02-02-2018
 */
public class Orchestrator {
    List<ActiveEntity> activeEntityList;
    List<Agent> agentList;

    /**
     * Default constructor
     */
    public Orchestrator() {
        agentList = new ArrayList<Agent>();
    }

    /**
     * Adds an agent to the system
     * @param agent the agent to add
     */
    public void createAgent(Agent agent) {
        agentList.add(agent);
    }

    /**
     * Adds an active entity to the system
     * @param activeEntity the active entity to add
     */
    public void createActiveEntity(ActiveEntity activeEntity) {
        activeEntityList.add(activeEntity);
    }

    /**
     * Runs all the agent lifecycle sequentially
     */
    public void run(IGlobalEnvironment globalEnv) {
        List<Agent> agentsToGarbage = new ArrayList<Agent>();

        for (Agent a : agentList) {
            // agentEnv is the maximal action scope of the agent
            IEnvironment agentEnv = globalEnv.getAgentEnvironment(a);
            a.runLifeCycle(agentEnv);

            if (this.agentIsDead(a)) {
                // we check if the agent is dead after the lifecycle 
                // because his state can only change during his lifecycle (INFRA-FN13)
                agentsToGarbage.add(a);
            }
            globalEnv.synchronize(agentEnv);
        }

        this.garbageAgents(agentsToGarbage);
    }

    public List<Agent> getListAgents() {
		return agentList;
    }

    /**
     * Remove the agents with a Dead state from the agents list in the system.
     * @param agents the list of agent to remove
     */
    private void garbageAgents(List<Agent> agents) {
        this.agentList.removeAll(agents);
    }

    /**
     * Verify if the agent is dead.
     * @param agent the agent to verify
     */
    private boolean agentIsDead(Agent agent) {
        return agent.getState().equals(State.DEAD);
    }
}
