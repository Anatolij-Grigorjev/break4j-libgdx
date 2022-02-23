package com.tiem625.break4j.utils.fsm;

import java.util.Objects;
import java.util.Optional;

public class State {

    private final StateId id;
    protected FiniteStateMachine<? extends State> ownerFsm;

    public State(StateId id) {
        this.id = Optional.ofNullable(id)
                .filter(StateId::isPresent)
                .orElseThrow(() -> new IllegalArgumentException("null/NONE StateId not allowed for state!"));
    }

    public StateId getId() {
        return id;
    }

    void enterState(StateId previousStateId) {
    }

    void exitState(StateId nextStateId) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return id.equals(state.id) && Objects.equals(ownerFsm, state.ownerFsm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerFsm);
    }
}
