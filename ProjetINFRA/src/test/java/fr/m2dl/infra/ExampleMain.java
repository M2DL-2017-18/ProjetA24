package fr.m2dl.infra;

import fr.m2dl.infra.examples.AgentTest;
import fr.m2dl.infra.examples.BehaviorTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExampleMain {
        public static void main(String[] args) throws InterruptedException {
            BlockingQueue q = new ArrayBlockingQueue(1024);

            Orchestrator orchestrator = new Orchestrator(q, new IEnvironment() {});
            orchestrator.createAgent(new AgentTest(new BehaviorTest()));

            ControlOrchestrator p = new ControlOrchestrator(q, orchestrator);

            new Thread(p).start();

            Thread.sleep(3000);

            p.pauseOrchestrator();
        }
}
