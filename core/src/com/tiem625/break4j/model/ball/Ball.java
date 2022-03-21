package com.tiem625.break4j.model.ball;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.model.bricks.SimpleBrick;

public class Ball {

    private Vector2 velocity;

    public Ball() {
        velocity = new Vector2();
    }

    public Vector2 getCurrentVelocity() {
        return velocity;
    }

    public void hitBrick(SimpleBrick brick) {
        brick.hitByBall(this);
        //TODO: add vector "bounce" with parameter to indicate brick hit side (for surface normal)
    }

    public void addImpulse(Vector2 additiveImpulse) {
        velocity = velocity.add(additiveImpulse);
    }
}
