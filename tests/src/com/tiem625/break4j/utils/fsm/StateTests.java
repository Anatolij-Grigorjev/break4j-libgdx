package com.tiem625.break4j.utils.fsm;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StateTests {

    @Test
    public void create_state_null_id_not_allowed() {

        assertThrows(IllegalArgumentException.class, () -> new State(null));
    }

    @Test
    public void create_state_id_NONE_not_allowed() {

        assertThrows(IllegalArgumentException.class, () -> new State(StateId.NONE));
    }

    @Test
    public void state_some_valid_id_created() {

        assertDoesNotThrow(() -> new State(StateId.ofForm("IDLE")));
    }

    @Test
    public void two_states_same_id_no_fsm_equal() {
        var state1 = new State(StateId.ofForm("1"));
        var state2 = new State(StateId.ofForm("1"));

        assertEquals(state1, state2);
    }

    @Test
    public void two_states_different_fsm_same_id_not_equal() {
        var state1 = new State(StateId.ofForm("1"));
        var state2 = new State(StateId.ofForm("1"));

        state1.ownerFsm = new FiniteStateMachine<>();
        state2.ownerFsm = new FiniteStateMachine<>();

        assertNotEquals(state1, state2);
    }
}
