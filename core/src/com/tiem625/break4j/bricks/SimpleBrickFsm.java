package com.tiem625.break4j.bricks;

import com.tiem625.break4j.utils.fsm.FiniteStateMachine;
import com.tiem625.break4j.utils.fsm.State;

import java.util.Set;

public class SimpleBrickFsm extends FiniteStateMachine<State> {

    private final SimpleBrick entity;

    public SimpleBrickFsm(SimpleBrick entity) {
        super(createBrickStates(entity), BrickStatesIds.BRICK_IDLE);
        this.entity = entity;
    }


    private static Set<State> createBrickStates(SimpleBrick entity) {
        return Set.of(
            new State(BrickStatesIds.BRICK_IDLE),
            new BrickBreakingState(entity)
        );
    }
}
