package com.tiem625.break4j.bricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tiem625.break4j.ball.Ball;
import com.tiem625.break4j.tools.HasAssets;
import com.tiem625.break4j.tools.fsm.State;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class SimpleBrick extends Actor implements HasAssets {

    private final BrickPosition brickPosition;
    private final SimpleBrickFsm fsm;

    public SimpleBrick(BrickPosition brickPosition, Color brickColor) {
        this.brickPosition = ofNullable(brickPosition)
                .orElseThrow(() -> new IllegalArgumentException("missing brickPosition!"));
        ofNullable(brickColor).ifPresentOrElse(
                this::setColor,
                () -> {
                    throw new IllegalArgumentException("missing color!");
                }
        );
        this.fsm = new SimpleBrickFsm(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        fsm.process(delta);
    }

    public Optional<State> getState() {
        return fsm.currentState();
    }

    public BrickPosition getBrickPosition() {
        return brickPosition;
    }

    public void hitByBall(Ball ball) {
        fsm.setState(BrickStatesIds.BRICK_BREAKING);
    }
}
