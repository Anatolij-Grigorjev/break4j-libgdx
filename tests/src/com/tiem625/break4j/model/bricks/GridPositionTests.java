package com.tiem625.break4j.model.bricks;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GridPositionTests {

    @Test
    public void create_brick_position_negative_numbers_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> GridPosition.atGridOffset(-1,-1));
    }

    @Test
    public void brick_positions_same_coords_equal() {
        assertEquals(GridPosition.atGridOffset(0, 6), GridPosition.atGridOffset(0, 6));
    }
}
