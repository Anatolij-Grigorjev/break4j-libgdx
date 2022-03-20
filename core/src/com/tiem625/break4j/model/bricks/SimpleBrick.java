package com.tiem625.break4j.model.bricks;

import com.tiem625.break4j.model.ObjectWithId;
import com.tiem625.break4j.model.ball.Ball;
import com.tiem625.break4j.tools.fsm.State;

import java.util.Optional;

public class SimpleBrick extends ObjectWithId {

    private final SimpleBrickFsm fsm;

    public SimpleBrick() {
        this.fsm = new SimpleBrickFsm(this);
    }

    public void advanceFSM(float deltaTime) {
        fsm.process(deltaTime);
    }

    public State getState() {
        return fsm.currentState().get();
    }

    public Optional<State> checkState() {
        return fsm.currentState();
    }

    public void hitByBall(Ball ball) {
        fsm.setState(BrickStatesIds.BRICK_BREAKING);
    }
}
