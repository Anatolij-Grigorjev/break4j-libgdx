package com.tiem625.break4j.tools.fsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FiniteStateMachineTests {

    private final StateId testStateId1 = StateId.ofForm("TEST_1");
    private final StateId testStateId2 = StateId.ofForm("TEST_2");

    @Test
    public void create_fsm_no_initial_states_no_current_set_empty() {

        var fsm = new FiniteStateMachine<>();

        assertEquals(Optional.empty(), fsm.currentState());
        assertEquals(Optional.empty(), fsm.previousState());
        assertEquals(Set.of(), fsm.getKnownStates());
    }

    @Test
    public void create_fsm_set_of_states_no_initial() {

        State state = new State(testStateId1);
        var fsm = new FiniteStateMachine<>(Set.of(state));

        assertEquals(Optional.empty(), fsm.currentState());
        assertEquals(Optional.empty(), fsm.previousState());
        assertEquals(1, fsm.getKnownStates().size());

        assertEquals(fsm, state.ownerFsm);
    }

    @Test
    public void create_fsm_set_of_states_initial_state_set_engages_lifecycle() {

        var state1 = spy(new State(testStateId1));

        var fsm = new FiniteStateMachine<>(Set.of(state1), state1.getId());

        assertEquals(Optional.of(testStateId1), fsm.currentState().map(State::getId));
        assertEquals(Optional.empty(), fsm.previousState());
        assertEquals(1, fsm.getKnownStates().size());

        //not testing fsm.setState call via spy because it happens in constructor
        verify(state1).enterState(eq(StateId.NONE));
    }

    @Test
    public void create_fsm_states_initial_state_must_be_present() {

        var states = mockStatesSet(testStateId1, testStateId2);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FiniteStateMachine<>(states, StateId.ofForm(UUID.randomUUID().toString()))
        );
    }

    @Test
    public void create_fsm_states_initial_NONE_ok() {
        var states = mockStatesSet(testStateId1, testStateId2);
        Assertions.assertDoesNotThrow(() -> new FiniteStateMachine<>(states, StateId.NONE));
    }

    @Test
    public void fsm_no_state_process_no_states_touched() {

        var states = mockStatesSet(testStateId1, testStateId2);
        var fsm = new FiniteStateMachine<>(states);

        fsm.process(1.0f);

        states.forEach(state -> verify(state, never()).process(anyFloat()));
    }

    @Test
    public void fsm_3_states_follows_lifecycle() {
        var idStart = StateId.ofForm("START");
        var idProcess = StateId.ofForm("PROCESS");
        var idEnd = StateId.ofForm("END");
        var states = mockStatesSet(idStart, idProcess, idEnd);

        var fsm = new FiniteStateMachine<>(states, idStart);
        fsm.setState(idProcess);

        assertEquals(Optional.of(idProcess), fsm.currentState().map(State::getId));
        assertEquals(Optional.of(idStart), fsm.previousState().map(State::getId));

        var startState = fsm.lookupState(idStart).get();
        var processState = fsm.lookupState(idProcess).get();

        verify(startState).exitState(idProcess);
        verify(processState).enterState(idStart);
    }

    @Test
    public void fsm_in_state_process_ticked_state() {

        var states = mockStatesSet(testStateId1, testStateId2);
        var fsm = new FiniteStateMachine<>(states, testStateId1);

        fsm.process(1.0f);

        verify(fsm.lookupState(testStateId1).get()).process(eq(1.0f));
    }


    private Set<State> mockStatesSet(String... stateIds) {
        return mockStatesSet(Arrays.stream(stateIds)
                .map(StateId::ofForm)
                .toArray(StateId[]::new));
    }

    private Set<State> mockStatesSet(StateId... stateIds) {
        return Arrays.stream(stateIds)
                .map(State::new)
                .map(Mockito::spy)
                .collect(Collectors.toSet());
    }
}
