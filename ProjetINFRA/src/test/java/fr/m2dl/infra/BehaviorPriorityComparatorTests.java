package fr.m2dl.infra;

import fr.m2dl.infra.behavior.Behavior;
import fr.m2dl.infra.behavior.BehaviorPriorityComparator;
import fr.m2dl.infra.seed.behaviors.GreetingBehavior;
import fr.m2dl.infra.seed.behaviors.SetStartingBehavior;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BehaviorPriorityComparatorTests {
    private static BehaviorPriorityComparator comparator;

    @BeforeAll
    static void initComparator() {
        comparator = new BehaviorPriorityComparator();
    }

    @Test
    void it_should_sort_by_none_natural_order() {
        // Given 2 behavior with different priority
        Behavior greeting = new GreetingBehavior();
        Behavior startingBehavior = new SetStartingBehavior();

        assertAll("Should have the correct comparisons",
            () -> assertEquals(1, comparator.compare(greeting, startingBehavior)),
            () -> assertEquals(-1, comparator.compare(startingBehavior, greeting)),
            () -> assertEquals(0, comparator.compare(greeting, greeting))
        );
    }
}