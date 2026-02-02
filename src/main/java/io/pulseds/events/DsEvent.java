package io.pulseds.events;

public record DsEvent(
        long timestampMs,
        long threadId,
        String source,
        EventType type,
        String message
) {}
