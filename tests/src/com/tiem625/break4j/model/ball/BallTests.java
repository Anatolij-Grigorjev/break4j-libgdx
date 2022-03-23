package com.tiem625.break4j.model.ball;

import com.tiem625.break4j.model.bricks.BrickSide;
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

        Assertions.assertTrue(ball.velocity().isInert());
    }

    @Test
    public void ball_idle_shoved_with_force_gains_velocity() {

        Ball ball = new Ball();
        Paddle paddle = new Paddle();

        paddle.startShoveBall(ball);

        Assertions.assertFalse(ball.velocity().isInert());
    }

    @Test
    public void ball_hit_brick_velocity_changed() {

        var ball = new Ball();
        var paddle = new Paddle();
        paddle.startShoveBall(ball);

        var prevVelocity = ball.velocity().asVector();
        ball.hitBrick(new SimpleBrick(), BrickSide.BOTTOM);

        Assertions.assertNotEquals(prevVelocity, ball.velocity().asVector());
    }

}
