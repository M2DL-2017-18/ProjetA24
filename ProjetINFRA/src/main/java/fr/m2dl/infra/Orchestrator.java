package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Scheduler of the Multi-Agent System
 *
 * It "runs" all the agents in the system
 * @author Infra core team
 * @since 16-02-2018
 */
public class Orchestrator {
    List<ActiveEntity> activeEntityList;
    List<Agent> agentList;
    AgentFactoriesRegistry registryFactories;

    /**
     * Default constructor
     */
    public Orchestrator() {
        agentList = new ArrayList<Agent>();
        registryFactories = new AgentFactoriesRegistry();
    }

    /**
     * Adds an agent to the system
     * @param agent the agent to add
     */
    public void createAgent(Agent agent) {
        agentList.add(agent);
    }

    /**
     * Register a new factory to create agent
     * @param factory the factory that can create Agent
     */
    public int addFactoryOfAgent(IFactoryAgent<Agent> factory) {
        return this.registryFactories.registerAgentFactory(factory);
    }

    /**
     * Create agents of the same type by using their factory.
     * @param token the token id of their factory
     */
    public boolean createAgents(int token, long count) {
        Optional<List<Agent>> agents = this.registryFactories.createSwarm(token, count);

        if (agents.isPresent()) {
            this.agentList.addAll(agents.get());
            return true;
        }

        return false;
    }

    /**
     * Create an agent by using his factory
     * @param token the token id of his factory
     */
    public boolean createAgent(int token) {
        Optional<Agent> agent = this.registryFactories.createAgent(token);

        if (agent.isPresent()) {
            this.agentList.add(agent.get());
            return true;
        }

        return false;
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
