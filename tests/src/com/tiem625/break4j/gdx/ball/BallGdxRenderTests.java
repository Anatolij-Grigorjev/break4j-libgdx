package com.tiem625.break4j.gdx.ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.bricks.BrickGdxRender;
import com.tiem625.break4j.model.ball.Ball;
import com.tiem625.break4j.model.bricks.BrickSide;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.testhelp.GdxHeadlessSupport;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(GdxHeadlessSupport.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BallGdxRenderTests {

    private Stage rootStage;

    @BeforeEach
    public void setupEmptyStage() {
        rootStage = mockStage();
    }

    @Test
    public void ball_collide_null_illegal_arg() {

        var ballRender = new BallGdxRender(new Ball());

        Assertions.assertThrows(IllegalArgumentException.class, () -> ballRender.checkCollisionWith(null));
    }

    @Test
    public void ball_collide_farawar_brick_empty_return() {

        var ballRender = new BallGdxRender(new Ball());
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        setElementsToStage(ballRender, brickRender);
        //ball at (0;0)
        ballRender.setLocalPosition(ScreenPosition.ORIGIN);
        //move brick away
        brickRender.setLocalPosition(ScreenPosition.at(500, 500));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isEmpty());
    }

    @Test
    public void ball_collide_brick_above_side_bottom() {
        var ballRender = new BallGdxRender(new Ball());
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        setElementsToStage(ballRender, brickRender);
        //ball at (0;0)
        ballRender.setLocalPosition(ScreenPosition.ORIGIN);
        //move brick just above ball
        var ballBounds = ballRender.localBounds();
        brickRender.setLocalPosition(ScreenPosition.at(ballBounds.x, ballBounds.y + ballBounds.height - 2));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.BOTTOM, collisionSide.get());
    }

    @Test
    public void ball_collide_brick_below_side_top() {

        var ballRender = new BallGdxRender(new Ball());
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        setElementsToStage(ballRender, brickRender);
        //ball at (0;0)
        ballRender.setLocalPosition(ScreenPosition.ORIGIN);
        //move brick just below ball
        var ballBounds = ballRender.localBounds();
        brickRender.setLocalPosition(ScreenPosition.at(ballBounds.x, ballBounds.y - brickRender.size().height() + 2));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.TOP, collisionSide.get());
    }

    @Test
    public void ball_collide_brick_right_side_left() {
        var ballRender = new BallGdxRender(new Ball());
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        setElementsToStage(ballRender, brickRender);
        //ball at (0;0)
        ballRender.setLocalPosition(ScreenPosition.ORIGIN);
        //move brick just to the right of ball
        var ballBounds = ballRender.localBounds();
        brickRender.setLocalPosition(ScreenPosition.at(ballBounds.x + ballBounds.width - 2, ballBounds.y));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.LEFT, collisionSide.get());
    }

    @Test
    public void ball_collide_brick_left_side_right() {
        var ballRender = new BallGdxRender(new Ball());
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());
        setElementsToStage(ballRender, brickRender);
        //ball at (0;0)
        ballRender.setLocalPosition(ScreenPosition.ORIGIN);
        //move brick just to the left of ball
        var ballBounds = ballRender.localBounds();
        brickRender.setLocalPosition(ScreenPosition.at(ballBounds.x - brickRender.size().width() + 2, ballBounds.y));

        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.RIGHT, collisionSide.get());
    }


    @Test
    public void ball_brick_different_groups_collision_checked_ok() {
        var ballRender = new BallGdxRender(new Ball());
        var brickRender = BrickGdxRender.renderModel(new SimpleBrick());

        var brickGroup = new Group();
        brickGroup.addActor(brickRender);

        setElementsToStage(ballRender, brickGroup);
        //ball at (0;0)
        ballRender.setLocalPosition(ScreenPosition.ORIGIN);
        //move brick just above ball
        var ballBounds = ballRender.localBounds();
        brickRender.setLocalPosition(ScreenPosition.at(ballBounds.x, ballBounds.y + ballBounds.height - 2));


        Optional<BrickSide> collisionSide = ballRender.checkCollisionWith(brickRender);

        Assertions.assertTrue(collisionSide.isPresent());
        Assertions.assertEquals(BrickSide.BOTTOM, collisionSide.get());
    }



    private Stage mockStage() {
        return new Stage(Mockito.mock(Viewport.class), Mockito.mock(SpriteBatch.class));
    }

    private void setElementsToStage(Actor... actors) {
        setElementsToStage(rootStage, actors);
    }
    private void setElementsToStage(Stage stage, Actor... actors) {
        Arrays.stream(actors).forEach(stage::addActor);
    }
}
