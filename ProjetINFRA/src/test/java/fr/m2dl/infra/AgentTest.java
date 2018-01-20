package fr.m2dl.infra;

import fr.m2dl.infra.environment.Environment;
import fr.m2dl.infra.seed.agents.SimpleAgent;
import fr.m2dl.infra.seed.agents.CarAgent;
import fr.m2dl.infra.seed.behaviors.MoveBehavior;
import fr.m2dl.infra.seed.behaviors.SetStartingBehavior;
import fr.m2dl.infra.seed.behaviors.SuicideBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentTest {
    @Test
    void it_should_run_the_behaviors_in_correct_order() {
        Agent agent = new SimpleAgent();
        // We add 2 different behavior with different level of priority
        // They should be reorder with [SetStartingBehavior, SuicideBehavior]
        // instead of [SuicideBehavior, SetStartingBehavior]
        agent.addBehavior(new SuicideBehavior()); // priority 10
        agent.addBehavior(new SetStartingBehavior()); // priority 1 000 000
        agent.update(new Environment());

        assertEquals(State.DEAD, agent.getInnerState());
    }

    @Test
    void it_should_be_able_to_access_and_modify_his_state() {
        //Given a new car (Agent) which can move
        CarAgent car = new CarAgent();
        car.addBehavior(new MoveBehavior());

        // When we update it
        car.update(new Environment());

        // Then the car moved
        assertAll("the car should move",
            () -> assertEquals(10, car.x),
            () -> assertEquals(10, car.y)
        );
    }
}