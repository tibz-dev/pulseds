package io.pulseds.engine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HeartbeatScheduler {

    private final ScheduledExecutorService exec =
            Executors.newSingleThreadScheduledExecutor();

    public void start(Engine engine, long periodMs) {
        exec.scheduleAtFixedRate(
                engine::heartbeat,
                0,
                periodMs,
                TimeUnit.MILLISECONDS
        );
    }

    public void stop() {
        exec.shutdownNow();
    }
}
