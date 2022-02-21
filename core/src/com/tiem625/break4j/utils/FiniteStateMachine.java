package com.tiem625.break4j.utils;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class FiniteStateMachine<S extends State> {

    private final Map<S, Boolean> knownStates;

    public FiniteStateMachine() {
        this(Set.of());
    }


    public FiniteStateMachine(Set<S> knownStates) {
        this.knownStates = knownStates.stream()
                .collect(toMap(identity(), ignored -> false));
    }

    public Optional<S> getCurrentState() {
        throw new UnsupportedOperationException("TODO");
    }

    public Optional<S> getPreviousState() {
        throw new UnsupportedOperationException("TODO");
    }

    public Set<S> getKnownStates() {
        return Set.copyOf(knownStates.keySet());
    }
}
