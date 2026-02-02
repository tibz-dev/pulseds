package io.pulseds.events;

public interface EventSink {
    void publish(DsEvent event);
}
