package com.tiem625.break4j.bricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tiem625.break4j.ball.Ball;
import com.tiem625.break4j.tools.AssetsLoader;
import com.tiem625.break4j.tools.HasAssets;
import com.tiem625.break4j.tools.fsm.State;

import java.util.Optional;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class SimpleBrick extends Actor implements HasAssets {

    private final SimpleBrickFsm fsm;

    public SimpleBrick(Color brickColor) {
        this.setColor(verifiedNotNull(brickColor));
        this.fsm = new SimpleBrickFsm(this);
    }

    @Override
    public void loadEntityAssets(AssetsLoader loader) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        fsm.process(delta);
    }

    public Optional<State> getState() {
        return fsm.currentState();
    }

    public void hitByBall(Ball ball) {
        fsm.setState(BrickStatesIds.BRICK_BREAKING);
    }
}
