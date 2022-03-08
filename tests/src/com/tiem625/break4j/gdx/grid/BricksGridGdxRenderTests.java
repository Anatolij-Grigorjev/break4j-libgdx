package com.tiem625.break4j.gdx.grid;

import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;
import com.tiem625.break4j.testhelp.GdxHeadlessSupport;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(GdxHeadlessSupport.class)
public class BricksGridGdxRenderTests {

    @Test
    public void create_grid_render_no_grid_model_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> new BricksGridGdxRender(null, ScreenPosition.ORIGIN));
    }

    @Test
    public void create_grid_render_no_position_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> new BricksGridGdxRender(new BricksGrid(GridDimensions.COLLAPSED), null));
    }
}
