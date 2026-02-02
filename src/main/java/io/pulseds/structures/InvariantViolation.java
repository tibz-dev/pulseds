package io.pulseds.structures;

public record InvariantViolation(
        String rule,
        String message
) {}
