package com.tiem625.break4j.bricks;

import com.badlogic.gdx.graphics.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SimpleBrickTests {

    @Test
    public void create_brick_no_color_not_allowed() {

        assertThrows(IllegalArgumentException.class, () -> new SimpleBrick(BrickPosition.ORIGIN, null));
    }

    @Test
    public void create_brick_no_position_not_allowed() {

        assertThrows(IllegalArgumentException.class, () -> new SimpleBrick(null, Color.BLUE));
    }

    @Test
    public void created_brick_in_state_idle() {

        var brick = new SimpleBrick(BrickPosition.ORIGIN, Color.BLUE);
        assertTrue(brick.getState().isPresent());
        assertEquals(BrickStatesIds.BRICK_IDLE, brick.getState().get().getId());
    }

    @Test
    public void created_brick_has_assigned_color() {

        var brick = new SimpleBrick(BrickPosition.ORIGIN, Color.YELLOW);
        assertEquals(Color.YELLOW, brick.getColor());
    }

    @Test
    public void brick_hit_by_ball_starts_breaking() {

        var brick = new SimpleBrick(BrickPosition.ORIGIN, Color.BLUE);
        brick.hitByBall(null);

        assertEquals(BrickStatesIds.BRICK_BREAKING, brick.getState().get().getId());
    }

    @Test
    public void brick_breaking_after_sometime_gone() {

        var brick = new SimpleBrick(BrickPosition.ORIGIN, Color.YELLOW);
        brick.hitByBall(null);

        assertTrue(brick.hasActions());
    }
}
