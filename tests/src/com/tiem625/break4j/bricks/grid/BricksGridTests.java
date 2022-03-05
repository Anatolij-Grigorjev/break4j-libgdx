package com.tiem625.break4j.bricks.grid;

import com.badlogic.gdx.graphics.Color;
import com.tiem625.break4j.bricks.GridPosition;
import com.tiem625.break4j.bricks.SimpleBrick;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BricksGridTests {

    @Test
    public void create_grid_no_dimensions_not_allowed() {

        assertThrows(IllegalArgumentException.class,
                () -> new BricksGrid(null));
    }

    @Test
    public void create_grid_not_bricks_is_empty() {

        var bricksGrid = assertDoesNotThrow(() ->
                new BricksGrid(GridDimensions.rowsAndCols(2, 2))
        );
        assertEquals(0, bricksGrid.getCurrentNumBricks());
    }

    @Test
    public void create_grid_add_brick_has_brick() {

        var bricksGrid = new BricksGrid(GridDimensions.rowsAndCols(1, 1));
        bricksGrid.setBrick(GridPosition.ORIGIN, new SimpleBrick(Color.BLUE));

        assertEquals(1, bricksGrid.getCurrentNumBricks());
        assertTrue(bricksGrid.checkBrickAt(GridPosition.ORIGIN).isPresent());
    }

    @Test
    public void create_grid_add_brick_add_another_same_position_only_one_brick() {

        var bricksGrid = new BricksGrid(GridDimensions.rowsAndCols(4, 4));
        bricksGrid.setBrick(GridPosition.ORIGIN, new SimpleBrick(Color.BLUE));
        bricksGrid.setBrick(GridPosition.ORIGIN, new SimpleBrick(Color.YELLOW));

        assertEquals(1, bricksGrid.getCurrentNumBricks());
        assertEquals(Color.YELLOW, bricksGrid.checkBrickAt(GridPosition.ORIGIN).get().getColor());
    }

    @Test
    public void create_grid_add_brick_bad_position_out_of_bounds() {

        var bricksGrid = new BricksGrid(GridDimensions.COLLAPSED);
        assertThrows(IllegalArgumentException.class,
                () -> bricksGrid.setBrick(GridPosition.atGridOffset(5, 7), new SimpleBrick(Color.YELLOW)));
    }
}
