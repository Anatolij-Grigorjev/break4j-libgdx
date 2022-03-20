package com.tiem625.break4j.model.ball;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.model.bricks.SimpleBrick;

public class Ball {


    public Vector2 getCurrentVelocity() {
        return new Vector2();
    }

    public void hitBrick(SimpleBrick brick) {
        brick.hitByBall(this);
    }
}
