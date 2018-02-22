package fr.m2dl.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * Scheduler of the Multi-Agent System
 *
 * It "runs" all the agents in the system
 * @author Infra core team
 * @since 16-02-2018
 */
public class Orchestrator extends Thread {
    List<ActiveEntity> activeEntityList;
    List<Agent> agentList;
    AgentFactoriesRegistry registryFactories;
    BlockingQueue queue;
    IEnvironment environment;

    /**
     * Default constructor
     */
    public Orchestrator(BlockingQueue queue, IEnvironment env) {
        this.queue = queue;
        this.environment = env;
        agentList = new ArrayList<Agent>();
        activeEntityList = new ArrayList<ActiveEntity>();
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
    public <A extends Agent> int addFactoryOfAgent(IFactoryAgent<A> factory) {
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
     * Runs all the agents and active entities lifecycle sequentially
     */
    public void runOrchestrator() {
        List<Agent> agentsToGarbage = new ArrayList<Agent>();

        for (Agent a : agentList) {
            a.runLifeCycle(environment);

            if (this.agentIsDead(a)) {
                // we check if the agent is dead after the lifecycle 
                // because his state can only change during his lifecycle (INFRA-FN13)
                agentsToGarbage.add(a);
            }
        }

        for(ActiveEntity activeEntity : this.activeEntityList) {
            activeEntity.runLifeCycle(environment);
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
        return agent.getState().equals(AgentState.DEAD);
    }

    @Override
    public void run() {
        int countRunOrchestrator = 1;
        try {
            while (true) {
                Object o = queue.take();
                System.out.println("CONSUMING : " + o.toString());

                OrchestratorState state = (OrchestratorState) o;

                switch(state) {
                    case START:
                        do {
                            System.out.println("RUN ORCHESTRATOR, PASS #"+countRunOrchestrator);
                            this.runOrchestrator();
                            countRunOrchestrator++;
                        } while (queue.isEmpty());
                        break;
                    case PAUSE:
                        System.out.println("PAUSING ORCHESTRATOR");
                        this.interrupt();
                        break;
                }
            }
        } catch (InterruptedException ex) {}
    }
}
