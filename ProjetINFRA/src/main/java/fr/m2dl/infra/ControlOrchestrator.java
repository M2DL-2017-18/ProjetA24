package fr.m2dl.infra;

import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class ControlOrchestrator extends Thread  {
    BlockingQueue queue;

    ControlOrchestrator(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(produceStart());
            this.sleep(10000);
            queue.put(producePause());
            this.interrupt();
        } catch (InterruptedException ex) {}
    }

    private OrchestratorState produceStart() {
        return OrchestratorState.START;
    }

    private OrchestratorState producePause() {
        return OrchestratorState.PAUSE;
    }
}
