package fr.m2dl.infra;

import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class ControlOrchestrator extends Thread  {
    BlockingQueue queue;
    Orchestrator orchestrator;

    ControlOrchestrator(BlockingQueue queue, Orchestrator orchestrator) {
        this.queue = queue;
        this.orchestrator = orchestrator;
    }

    @Override
    public void run() {
        startOrchestrator();

        try {
            queue.put(produceStart());
            this.interrupt();
        } catch (InterruptedException ex) {}
    }

    private void startOrchestrator() {
        orchestrator.start();
    }

    public void pauseOrchestrator() throws InterruptedException {
        queue.put(producePause());
    }

    private OrchestratorState produceStart() {
        return OrchestratorState.START;
    }

    private OrchestratorState producePause() {
        return OrchestratorState.PAUSE;
    }
}
