package com.tiem625.break4j.model.grid;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GridDimensionsTests {

    @Test
    public void set_dimensions_rows_negative_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> GridDimensions.rowsAndCols(-5, 5));
    }

    @Test
    public void set_dimensions_cols_negative_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> GridDimensions.rowsAndCols(5, -5));
    }

    @Test
    public void set_dimension_0_ok() {
        assertDoesNotThrow(() -> GridDimensions.rowsAndCols(0, 0));
    }

    @Test
    public void same_dimensions_equal_objects() {
        assertEquals(GridDimensions.COLLAPSED, GridDimensions.rowsAndCols(0, 0));
    }

    @Test
    public void dimensions_have_0_is_collapsed_true() {
        assertTrue(GridDimensions.COLLAPSED.isCollapsed());
        assertTrue(GridDimensions.rowsAndCols(0, 6).isCollapsed());
        assertTrue(GridDimensions.rowsAndCols(6, 0).isCollapsed());
    }

    @Test
    public void dimensions_not_0_not_collapsed() {
        assertFalse(GridDimensions.rowsAndCols(1, 1).isCollapsed());
    }
}
