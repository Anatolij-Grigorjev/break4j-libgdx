package com.tiem625.break4j.model.bricks;

import com.tiem625.break4j.tools.fsm.StateId;

import java.util.Set;

public class BrickStatesIds {

    private BrickStatesIds() {
        throw new IllegalStateException("static data");
    }

    public static final StateId BRICK_BREAKING = StateId.ofForm("BrickBreaking");
    public static final StateId BRICK_IDLE = StateId.ofForm("BrickIdle");


    public static Set<StateId> asSet() {
        return Set.of(
                BRICK_IDLE,
                BRICK_BREAKING
        );
    }
}
