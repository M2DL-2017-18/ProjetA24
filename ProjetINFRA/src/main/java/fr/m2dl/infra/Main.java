package fr.m2dl.infra;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue q = new ArrayBlockingQueue(1024);

        ControlOrchestrator p = new ControlOrchestrator(q);

        Orchestrator c = new Orchestrator(q, new IEnvironment() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        new Thread(p).start();
        new Thread(c).start();
    }
}
