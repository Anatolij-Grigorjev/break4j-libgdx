package com.tiem625.break4j.bricks;

import com.badlogic.gdx.graphics.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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


}
