package com.tiem625.break4j.model.paddle;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.model.ball.Ball;

public class Paddle {


    public void startShoveBall(Ball ball) {
        ball.addImpulse(makeShoveUpwardsImpulse());
    }




    private Vector2 makeShoveUpwardsImpulse() {
        return new Vector2(
                MathUtils.random(-35.7f, 32.4f),
                MathUtils.random(25f, 35f)
        );
    }
}
