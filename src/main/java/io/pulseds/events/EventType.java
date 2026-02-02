package io.pulseds.events;

public enum EventType {

    // Engine lifecycle
    ENGINE_START,
    ENGINE_STOP,
    HEARTBEAT,

    // Data structure operations
    NODE_CREATE,
    INSERT,
    DELETE,

    // AVL-specific mechanics
    ROTATE_L,
    ROTATE_R,
    REBALANCE,

    // Diagnostics
    METRIC,
    VIOLATION
}
