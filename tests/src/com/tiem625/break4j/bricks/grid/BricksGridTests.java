package com.tiem625.break4j.bricks.grid;

import com.badlogic.gdx.graphics.Color;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.bricks.BrickPosition;
import com.tiem625.break4j.bricks.SimpleBrick;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BricksGridTests {

    @Test
    public void create_grid_no_position_not_allowed() {

        assertThrows(IllegalArgumentException.class,
                () -> new BricksGrid(null, GridDimensions.COLLAPSED));
    }

    @Test
    public void create_grid_no_dimensions_not_allowed() {

        assertThrows(IllegalArgumentException.class,
                () -> new BricksGrid(ScreenPosition.ORIGIN, null));
    }

    @Test
    public void create_grid_not_bricks_is_empty() {

        var bricksGrid = assertDoesNotThrow(() ->
                new BricksGrid(ScreenPosition.at(250, 650),
                        GridDimensions.rowsAndCols(2, 2))
        );
        assertEquals(0, bricksGrid.getCurrentNumBricks());
    }

    @Test
    public void create_grid_add_brick_has_brick() {

        var bricksGrid = new BricksGrid(ScreenPosition.at(250, 650), GridDimensions.COLLAPSED);
        bricksGrid.setBrick(BrickPosition.ORIGIN, new SimpleBrick(Color.BLUE));

        assertEquals(1, bricksGrid.getCurrentNumBricks());
    }
}
