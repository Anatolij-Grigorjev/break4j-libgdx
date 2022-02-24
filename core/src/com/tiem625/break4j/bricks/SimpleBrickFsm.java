package com.tiem625.break4j.bricks;

import com.tiem625.break4j.utils.fsm.FiniteStateMachine;
import com.tiem625.break4j.utils.fsm.State;
import com.tiem625.break4j.utils.fsm.StateId;

import java.util.Set;
import java.util.stream.Collectors;

public class SimpleBrickFsm extends FiniteStateMachine<State> {

    public SimpleBrickFsm() {
        super(createStatesWithIds(BrickStatesIds.asSet()), BrickStatesIds.BRICK_IDLE);
    }


    private static Set<State> createStatesWithIds(Set<StateId> idsSet) {
        return idsSet.stream()
                .map(State::new)
                .collect(Collectors.toSet());
    }
}
