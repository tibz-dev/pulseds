package io.pulseds.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.pulseds.structures.RealTimeAvl;

public final class ConcurrentLoadEngine 
{

    private final ScheduledExecutorService scheduler;
    private final ExecutorService workers;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public ConcurrentLoadEngine(int workerThreads) 
    {
        this.scheduler = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("pulse-scheduler"));
        this.workers = Executors.newFixedThreadPool(workerThreads, new NamedThreadFactory("pulse-worker"));
    }

    public void start(RealTimeAvl avl, long submitEveryMs, int opsPerTick) 
    {
        if (!running.compareAndSet(false, true)) return;

        scheduler.scheduleAtFixedRate(() -> 
        {
            for (int i = 0; i < opsPerTick; i++) 
                {
                workers.submit(() -> {
                    int v = ThreadLocalRandom.current().nextInt(1, 100_000);
                    avl.insert(v);
                });
            }
        }, 0, submitEveryMs, TimeUnit.MILLISECONDS);
    }

    public void stop() 
    {
        running.set(false);
        scheduler.shutdownNow();
        workers.shutdownNow();
    }
}
