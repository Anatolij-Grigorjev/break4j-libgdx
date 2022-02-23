package com.tiem625.break4j.utils.fsm;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class FiniteStateMachine<S extends State> {

    private final Map<StateId, S> knownStatesCache;
    private S currentState;
    private S previousState;

    public FiniteStateMachine() {
        this(Set.of());
    }


    public FiniteStateMachine(Set<S> knownStates) {
        this(knownStates, StateId.NONE);
    }

    public FiniteStateMachine(Set<S> knownStates, StateId initialStateId) {
        this.knownStatesCache = knownStates.stream()
                .peek(state -> state.ownerFsm = this)
                .collect(toMap(State::getId, identity()));
        setState(initialStateId);
    }

    public void process(float delta) {
        currentState().ifPresent(current -> current.process(delta));
        getNextStateId(delta).ifPresent(this::setState);
    }

    public Optional<S> currentState() {
        return ofNullable(currentState);
    }

    public Optional<S> previousState() {
        return ofNullable(previousState);
    }

    public Set<S> getKnownStates() {
        return Set.copyOf(knownStatesCache.values());
    }

    public Optional<S> lookupState(StateId withId) {
        return ofNullable(knownStatesCache.get(withId));
    }

    public void setState(StateId nextStateId) {
        if (Objects.equals(nextStateId, StateId.NONE)) {
            return;
        }

        var nextState = lookupState(nextStateId)
                .orElseThrow(() -> new IllegalArgumentException("No state know with id " + nextStateId));
        previousState = currentState;
        currentState = nextState;
        previousState().ifPresent(state -> state.exitState(nextStateId));
        currentState.enterState(previousState().map(State::getId).orElse(StateId.NONE));
    }

    protected Optional<StateId> getNextStateId(float delta) {
        return of(StateId.NONE);
    }
}
