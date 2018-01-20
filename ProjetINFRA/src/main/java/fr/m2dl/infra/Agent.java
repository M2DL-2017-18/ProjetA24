package fr.m2dl.infra;

import fr.m2dl.infra.behavior.Behavior;
import fr.m2dl.infra.behavior.BehaviorPriorityComparator;
import fr.m2dl.infra.environment.Environment;
import java.io.Serializable;
import java.util.PriorityQueue;
import java.util.UUID;

public abstract class Agent implements Serializable {
    private UUID id;
    private State innerState;
    private PriorityQueue<Behavior> behaviors;

    public Agent() {
        this.id = UUID.randomUUID();
        this.innerState = State.ALIVE;
        this.behaviors = new PriorityQueue<>(3, new BehaviorPriorityComparator());
    }

    public void update(Environment environment) {
        this.behaviors.forEach(b -> b.run(this, environment));
    }

    public void addBehavior(Behavior behavior) {
        this.behaviors.add(behavior);
    }

    public UUID getId() {
        return id;
    }

    public State getInnerState() {
        return innerState;
    }

    public void setInnerState(State innerState) {
        this.innerState = innerState;
    }
}