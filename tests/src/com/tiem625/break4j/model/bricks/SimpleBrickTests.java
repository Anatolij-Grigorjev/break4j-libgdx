package com.tiem625.break4j.model.bricks;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SimpleBrickTests {

    @Test
    public void created_brick_in_state_idle() {

        var brick = new SimpleBrick();
        assertTrue(brick.getState().isPresent());
        assertEquals(BrickStatesIds.BRICK_IDLE, brick.getState().get().getId());
    }

    @Test
    public void brick_hit_by_ball_starts_breaking() {

        var brick = new SimpleBrick();
        brick.hitByBall(null);

        assertEquals(BrickStatesIds.BRICK_BREAKING, brick.getState().get().getId());
    }
}
