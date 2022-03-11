package com.tiem625.break4j.model.grid;

import com.tiem625.break4j.model.bricks.SimpleBrick;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        bricksGrid.setBrick(GridPosition.ORIGIN, new SimpleBrick());

        assertEquals(1, bricksGrid.getCurrentNumBricks());
        assertTrue(bricksGrid.checkBrickAt(GridPosition.ORIGIN).isPresent());
    }

    @Test
    public void create_grid_add_brick_add_another_same_position_only_one_brick() {

        var bricksGrid = new BricksGrid(GridDimensions.rowsAndCols(4, 4));
        SimpleBrick brick1 = new SimpleBrick();
        SimpleBrick brick2 = new SimpleBrick();
        bricksGrid.setBrick(GridPosition.ORIGIN, brick1);
        bricksGrid.setBrick(GridPosition.ORIGIN, brick2);

        assertEquals(1, bricksGrid.getCurrentNumBricks());
        assertEquals(brick2, bricksGrid.checkBrickAt(GridPosition.ORIGIN).get());
    }

    @Test
    public void create_grid_add_brick_bad_position_out_of_bounds() {

        var bricksGrid = new BricksGrid(GridDimensions.COLLAPSED);
        assertThrows(IllegalArgumentException.class,
                () -> bricksGrid.setBrick(GridPosition.atGridOffset(5, 7), new SimpleBrick()));
    }

    @Test
    public void create_grid_set_brick_null_position_illegal() {

        var bricksGrid = new BricksGrid(GridDimensions.COLLAPSED);
        assertThrows(IllegalArgumentException.class,
                () -> bricksGrid.setBrick(null, new SimpleBrick()));
    }

    @Test
    public void create_grid_no_bricks_remove_brick_empty_removal() {

        var bricksGrid = new BricksGrid(GridDimensions.rowsAndCols(1, 1));
        Optional<SimpleBrick> cachedRemovedBrick = bricksGrid.removeBrick(GridPosition.ORIGIN);

        assertTrue(cachedRemovedBrick.isEmpty());
    }

    @Test
    public void create_grid_add_brick_remove_brick_present() {

        var bricksGrid = new BricksGrid(GridDimensions.rowsAndCols(1, 1));
        bricksGrid.setBrick(GridPosition.ORIGIN, new SimpleBrick());

        Optional<SimpleBrick> cachedRemovedBrick = bricksGrid.removeBrick(GridPosition.ORIGIN);
        assertTrue(cachedRemovedBrick.isPresent());
        assertEquals(0, bricksGrid.getCurrentNumBricks());
    }

    @Test
    public void create_grid_remove_brick_out_of_bounds_throws() {

        var bricksGrid = new BricksGrid(GridDimensions.COLLAPSED);
        assertThrows(IllegalArgumentException.class,
                () -> bricksGrid.removeBrick(GridPosition.atGridOffset(5, 7)));
    }

    @Test
    public void create_grid_remove_brick_null_position_not_allowed() {

        var bricksGrid = new BricksGrid(GridDimensions.COLLAPSED);
        assertThrows(IllegalArgumentException.class, () -> bricksGrid.removeBrick(null));
    }
}
