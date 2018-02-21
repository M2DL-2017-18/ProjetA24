package fr.m2dl.infra.examples;

import fr.m2dl.infra.IFactoryAgent;

public class FactoryForAgentTest implements IFactoryAgent<AgentTest> {

    @Override
    public AgentTest create() {
        return new AgentTest(null);
    }
}