package fr.m2dl.infra;

/**
 * Describes what is a runnable entity
 */
public interface RunnableEntity {
    /**
     * Runs the lifecycle of an entity
     *
     * @param env environment of the entity
     */
    public void runLifeCycle(IEnvironment env);
}
