package com.tiem625.break4j.bricks;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BrickPositionTests {

    @Test
    public void create_brick_position_negative_numbers_allowed() {
        assertDoesNotThrow(() -> BrickPosition.atGridOffset(-1,-1));
    }

    @Test
    public void brick_positions_same_coords_equal() {
        assertEquals(BrickPosition.atGridOffset(0, 6), BrickPosition.atGridOffset(0, 6));
    }
}
