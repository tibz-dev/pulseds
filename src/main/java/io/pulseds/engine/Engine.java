package io.pulseds.engine;

import java.util.concurrent.atomic.AtomicBoolean;

import io.pulseds.events.DsEvent;
import io.pulseds.events.EventSink;
import io.pulseds.events.EventType;

public class Engine {

    private final EventSink sink;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public Engine(EventSink sink) {
        this.sink = sink;
    }

    public void start() {
        running.set(true);
        emit(EventType.ENGINE_START, "Engine started");
    }

    public void stop() {
        running.set(false);
        emit(EventType.ENGINE_STOP, "Engine stopped");
    }

    public boolean isRunning() {
        return running.get();
    }

    public void heartbeat() {
        if (running.get()) {
            emit(EventType.HEARTBEAT, "Pulse");
        }
    }

    private void emit(EventType type, String msg) {
        sink.publish(new DsEvent(
                System.currentTimeMillis(),
                Thread.currentThread().getId(),
                "ENGINE",
                type,
                msg
        ));
    }
}
