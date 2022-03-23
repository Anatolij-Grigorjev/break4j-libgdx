package com.tiem625.break4j.model.ball;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.model.bricks.BrickSide;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BallBounceTests {

    private Ball ball;

    @BeforeEach
    public void startBall() {
        ball = new Ball();
    }

    @Test
    public void ball_bounce_brick_bottom_velocity_downwards() {
        Vector2 ballInitVelocity = new Vector2(10, 10);
        ball.addImpulse(ballInitVelocity);

        ball.hitBrick(new SimpleBrick(), BrickSide.BOTTOM);

        Assertions.assertNotEquals(Math.signum(ballInitVelocity.y), Math.signum(ball.velocity().direction().y));
        Assertions.assertTrue(Math.signum(ball.velocity().direction().y) < 0);
    }

    @Test
    public void ball_bounce_brick_top_velocity_upwards() {
        Vector2 ballInitVelocity = new Vector2(10, -10);
        ball.addImpulse(ballInitVelocity);

        ball.hitBrick(new SimpleBrick(), BrickSide.TOP);

        Assertions.assertNotEquals(Math.signum(ballInitVelocity.y), Math.signum(ball.velocity().direction().y));
        Assertions.assertTrue(Math.signum(ball.velocity().direction().y) > 0);
    }

    @Test
    public void ball_bounce_brick_left_velocity_left() {
        Vector2 ballInitVelocity = new Vector2(10, 10);
        ball.addImpulse(ballInitVelocity);

        ball.hitBrick(new SimpleBrick(), BrickSide.LEFT);

        Assertions.assertNotEquals(Math.signum(ballInitVelocity.x), Math.signum(ball.velocity().direction().x));
        Assertions.assertTrue(Math.signum(ball.velocity().direction().x) < 0);
    }

    @Test
    public void ball_bounce_brick_right_velocity_right() {
        Vector2 ballInitVelocity = new Vector2(-10, 10);
        ball.addImpulse(ballInitVelocity);

        ball.hitBrick(new SimpleBrick(), BrickSide.RIGHT);

        Assertions.assertNotEquals(Math.signum(ballInitVelocity.x), Math.signum(ball.velocity().direction().x));
        Assertions.assertTrue(Math.signum(ball.velocity().direction().x) > 0);
    }
}
