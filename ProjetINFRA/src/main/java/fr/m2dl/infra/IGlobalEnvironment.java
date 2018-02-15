package fr.m2dl.infra;

public interface IGlobalEnvironment<A extends Agent, E extends IEnvironment> extends IEnvironment {
    E getAgentEnvironment(A a);
    void synchronize(E environment);
}
