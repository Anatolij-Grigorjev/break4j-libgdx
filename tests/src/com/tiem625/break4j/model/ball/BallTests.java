package com.tiem625.break4j.model.ball;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.paddle.Paddle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BallTests {

    @Test
    public void create_ball_ok_velocity_0() {

        Ball ball = new Ball();

        Assertions.assertEquals(new Vector2(), ball.getCurrentVelocity());
    }

    @Test
    public void ball_idle_shoved_with_force_gains_velocity() {

        Ball ball = new Ball();
        Paddle paddle = new Paddle();

        paddle.startShoveBall(ball);

        Assertions.assertNotEquals(new Vector2(), ball.getCurrentVelocity());
    }

    @Test
    public void ball_hit_brick_velocity_changed() {

        var ball = new Ball();
        var paddle = new Paddle();
        paddle.startShoveBall(ball);

        var prevVelocity = ball.getCurrentVelocity();
        ball.hitBrick(new SimpleBrick());

        Assertions.assertNotEquals(prevVelocity, ball.getCurrentVelocity());
    }
}
