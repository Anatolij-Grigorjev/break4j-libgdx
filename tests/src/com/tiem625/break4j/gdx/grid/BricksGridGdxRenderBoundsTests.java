package com.tiem625.break4j.gdx.grid;

import com.tiem625.break4j.ObjectSize;
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

@ExtendWith(GdxHeadlessSupport.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BricksGridGdxRenderBoundsTests {

    @Test
    public void create_collapsed_render_position_as_specified() {
        var gridRender = BricksGridGdxRender.forModel(new BricksGrid(GridDimensions.COLLAPSED))
                .centeredAt(ScreenPosition.at(100, 100))
                .render();

        assertEquals(100f, gridRender.getX());
        assertEquals(100f, gridRender.getY());
        assertEquals(ObjectSize.COLLAPSED, gridRender.onScreenSize());
    }

    @Test
    public void create_1_brick_render_position_offset_to_brick_center() {
        var model = new BricksGrid(GridDimensions.rowsAndCols(1, 1));
        model.setBrick(GridPosition.ORIGIN, new SimpleBrick());
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();

        assertEquals(55f, gridRender.getX());
        assertEquals(55f, gridRender.getY());
        assertEquals(ObjectSize.widthAndHeight(90, 90), gridRender.onScreenSize());
    }

    @Test
    public void create_4_brick_render_position_centered_between_rows_cols() {
        var model = new BricksGrid(GridDimensions.rowsAndCols(2, 2));
        model.setBrick(GridPosition.ORIGIN, new SimpleBrick());
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();

        assertEquals(10f, gridRender.getX());
        assertEquals(10f, gridRender.getY());
        assertEquals(ObjectSize.widthAndHeight(180, 180), gridRender.onScreenSize());
    }

    @Test
    public void create_9_bricks_render_with_gaps_center_and_size() {
        var model = new BricksGrid(GridDimensions.rowsAndCols(3, 3));
        model.setBrick(GridPosition.ORIGIN, new SimpleBrick());
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .withHorizontalGap(10)
                .withVerticalGap(5)
                .render();

        assertEquals(-45f, gridRender.getX());
        assertEquals(-40f, gridRender.getY());
        assertEquals(ObjectSize.widthAndHeight(290, 280), gridRender.onScreenSize());
    }

}
