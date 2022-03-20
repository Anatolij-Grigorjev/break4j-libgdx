package com.tiem625.break4j.model.bricks;

import com.tiem625.break4j.model.ball.Ball;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SimpleBrickTests {

    @Test
    public void created_brick_in_state_idle() {

        var brick = new SimpleBrick();

        assertNotNull(brick.getId());
        assertTrue(brick.checkState().isPresent());
        assertEquals(BrickStatesIds.BRICK_IDLE, brick.getState().getId());
    }

    @Test
    public void brick_hit_by_ball_starts_breaking() {

        var brick = new SimpleBrick();
        var ball  = new Ball();
        ball.hitBrick(brick);

        assertEquals(BrickStatesIds.BRICK_BREAKING, brick.getState().getId());
    }
}
