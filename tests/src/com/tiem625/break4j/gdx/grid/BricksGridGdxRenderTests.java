package com.tiem625.break4j.gdx.grid;

import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;
import com.tiem625.break4j.model.grid.GridPosition;
import com.tiem625.break4j.testhelp.GdxHeadlessSupport;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(GdxHeadlessSupport.class)
public class BricksGridGdxRenderTests {

    @Test
    public void create_grid_render_no_grid_model_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> BricksGridGdxRender.forModel(null));
    }

    @Test
    public void create_grid_render_no_set_position_uses_origin() {
        BricksGridGdxRender render = BricksGridGdxRender.forModel(new BricksGrid(GridDimensions.COLLAPSED))
                .render();

        assertEquals(0f, render.getX());
        assertEquals(0f, render.getY());
    }

    @Test
    public void create_grid_render_no_set_gaps_default_0() {
        BricksGridGdxRender render = BricksGridGdxRender.forModel(new BricksGrid(GridDimensions.COLLAPSED))
                .centeredAt(ScreenPosition.at(100, 100))
                .render();

        assertEquals(0, render.getVerticalGap());
        assertEquals(0, render.getHorizontalGap());
    }

    @Test
    public void create_grid_set_properties_present() {
        BricksGridGdxRender render = BricksGridGdxRender.forModel(new BricksGrid(GridDimensions.COLLAPSED))
                .centeredAt(ScreenPosition.at(100, 100))
                .withHorizontalGap(5)
                .withVerticalGap(10)
                .render();

        assertEquals(100f, render.getY());
        assertEquals(100f, render.getX());
        assertEquals(5, render.getHorizontalGap());
        assertEquals(10, render.getVerticalGap());
    }

    @Test
    public void create_grid_render_model_has_bricks_render_has_same_bricks() {

        var modelGrid = new BricksGrid(GridDimensions.rowsAndCols(1, 1));
        modelGrid.setBrick(GridPosition.ORIGIN, new SimpleBrick());

        var gridRender = BricksGridGdxRender.forModel(modelGrid).render();
        assertEquals(modelGrid.getCurrentNumBricks(), gridRender.getCurrentBricksLandscape().size());
    }
}
