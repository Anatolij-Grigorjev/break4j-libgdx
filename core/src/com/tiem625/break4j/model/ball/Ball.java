package com.tiem625.break4j.model.ball;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.model.bricks.BrickSide;
import com.tiem625.break4j.model.bricks.SimpleBrick;

public class Ball {

    private final Velocity velocity;

    public Ball() {
        velocity = Velocity.inert();
    }

    public Velocity velocity() {
        return velocity;
    }

    public void hitBrick(SimpleBrick brick, BrickSide hitSide) {
        brick.hitByBall(this);
        velocity.bounce(hitSide.surfaceNormal());
    }

    public void addImpulse(Vector2 additiveImpulse) {
        velocity.increaseBy(additiveImpulse);
    }
}
