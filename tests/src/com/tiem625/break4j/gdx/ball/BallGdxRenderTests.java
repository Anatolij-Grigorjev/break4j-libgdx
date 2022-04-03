package com.tiem625.break4j.gdx.ball;

import com.badlogic.gdx.math.Rectangle;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.bricks.BrickGdxRender;
import com.tiem625.break4j.model.ball.Ball;
import com.tiem625.break4j.model.bricks.BrickSide;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.testhelp.GdxHeadlessSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(GdxHeadlessSupport.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BallGdxRenderTests {

    @Test
    public void ball_collide_null_illegal_arg() {

        var ballRender = new BallGdxRender(new Ball());

        Assertions.assertThrows(IllegalArgumentException.class, () -> ballRender.checkCollisionWith(null));
    }

    @Test
    public void ball_collide_farawar_brick_empty_return() {

        var ballRender = new BallGdxRender(new Ball());

        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        //move brick away
        brickRender.setPosition(500, 500);

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isEmpty());
    }

    @Test
    public void ball_collide_brick_above_side_bottom() {
        var ballRender = new BallGdxRender(new Ball());
        Rectangle ballBounds = ballRender.globalBounds();
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        brickRender.setGlobalPosition(ScreenPosition.at(ballBounds.x, ballBounds.y + ballBounds.height - 2));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.BOTTOM, collisionSide.get());
    }

    @Test
    public void ball_collide_brick_below_side_top() {

        var ballRender = new BallGdxRender(new Ball());
        Rectangle ballBounds = ballRender.globalBounds();
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        brickRender.setGlobalPosition(ScreenPosition.at(ballBounds.x, ballBounds.y - brickRender.size().height() + 2));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.TOP, collisionSide.get());
    }

    @Test
    public void ball_collide_brick_right_side_left() {
        var ballRender = new BallGdxRender(new Ball());
        Rectangle ballBounds = ballRender.globalBounds();
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        brickRender.setGlobalPosition(ScreenPosition.at(ballBounds.x + ballBounds.width - 2, ballBounds.y));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.LEFT, collisionSide.get());
    }

    @Test
    public void ball_collide_brick_left_side_right() {
        var ballRender = new BallGdxRender(new Ball());
        Rectangle ballBounds = ballRender.globalBounds();
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        brickRender.setGlobalPosition(ScreenPosition.at(ballBounds.x - brickRender.size().width() + 2, ballBounds.y));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.RIGHT, collisionSide.get());
    }
}
