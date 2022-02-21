package com.tiem625.break4j.utils;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FiniteStateMachineTests {

    @Test
    public void create_fsm_no_initial_states_no_current_set_empty() {

        var fsm = new FiniteStateMachine<>();

        assertEquals(Optional.empty(), fsm.getCurrentState());
        assertEquals(Optional.empty(), fsm.getPreviousState());
        assertEquals(Set.of(), fsm.getKnownStates());
    }

    @Test
    public void create_fsm_set_of_states_no_initial() {

        var fsm = new FiniteStateMachine<>(Set.of(new State()));

        assertEquals(Optional.empty(), fsm.getCurrentState());
        assertEquals(Optional.empty(), fsm.getPreviousState());
        assertEquals(1, fsm.getKnownStates().size());
    }

}
