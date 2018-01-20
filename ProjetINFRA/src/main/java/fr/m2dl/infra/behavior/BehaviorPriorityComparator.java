package fr.m2dl.infra.behavior;

import java.util.Comparator;

public class BehaviorPriorityComparator implements Comparator<Behavior> {
    @Override
    // Use non natural order
    public int compare(Behavior b1, Behavior b2) {
        if (b1.getPriority() < b2.getPriority())
            return 1;
        if (b1.getPriority() > b2.getPriority())
            return -1;

        return 0;
    }
}
