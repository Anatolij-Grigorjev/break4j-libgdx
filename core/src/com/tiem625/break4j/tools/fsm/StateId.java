package com.tiem625.break4j.tools.fsm;

import java.util.Objects;

import static java.util.Optional.ofNullable;

public class StateId {

    public static final StateId NONE = StateId.ofForm("XXX-NOT-A-STATE-XXX");

    private final String serialized;

    private StateId(String data) {
        this.serialized = data;
    }

    public static StateId ofForm(String id) {
        return ofNullable(id)
                .map(StateId::new)
                .orElseThrow(() -> new IllegalArgumentException("Cannot create state with null id"));
    }

    public String serialized() {
        return serialized;
    }

    public boolean isPresent() {
        return this != NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateId stateId = (StateId) o;
        return serialized.equals(stateId.serialized);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialized);
    }

    @Override
    public String toString() {
        return serialized();
    }
}
