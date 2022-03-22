package com.tiem625.break4j.model.ball;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.model.bricks.BrickSide;
import com.tiem625.break4j.model.bricks.SimpleBrick;

public class Ball {

    private Vector2 velocity;

    public Ball() {
        velocity = new Vector2();
    }

    public Vector2 getCurrentVelocity() {
        return velocity;
    }

    public void hitBrick(SimpleBrick brick, BrickSide hitSide) {
        brick.hitByBall(this);
        velocity = reflectVectorAcrossSurfaceNormal(velocity, hitSide.surfaceNormal());
    }

    public void addImpulse(Vector2 additiveImpulse) {
        velocity = velocity.add(additiveImpulse);
    }


    private Vector2 reflectVectorAcrossSurfaceNormal(Vector2 vector, Vector2 surfaceNormal) {
        //   𝑟=𝑑−2(𝑑⋅𝑛)𝑛
        var scalarCoef = 2 * surfaceNormal.dot(vector);
        var substrVector = new Vector2(surfaceNormal.x, surfaceNormal.y).scl(scalarCoef);
        return new Vector2(vector.x, vector.y).sub(substrVector);
    }
}
