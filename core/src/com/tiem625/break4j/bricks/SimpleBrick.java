package com.tiem625.break4j.bricks;

import com.tiem625.break4j.ball.Ball;
import com.tiem625.break4j.tools.fsm.State;

import java.util.Optional;

public class SimpleBrick {

    private final SimpleBrickFsm fsm;

    public SimpleBrick() {
        this.fsm = new SimpleBrickFsm(this);
    }

    public void advanceFSM(float deltaTime) {
        fsm.process(deltaTime);
    }

    public Optional<State> getState() {
        return fsm.currentState();
    }

    public void hitByBall(Ball ball) {
        fsm.setState(BrickStatesIds.BRICK_BREAKING);
    }
}
