package com.tiem625.break4j.gdx.grid;

import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.grid.BricksGridGdxRender.BricksLandscape;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;
import com.tiem625.break4j.model.grid.GridPosition;
import com.tiem625.break4j.testhelp.GdxHeadlessSupport;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        var model = buildGridWithDimensionsAndBricksAtPositions(1, 1, Set.of(GridPosition.ORIGIN));
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();

        assertEquals(55f, gridRender.getX());
        assertEquals(55f, gridRender.getY());
        assertEquals(ObjectSize.widthAndHeight(90, 90), gridRender.onScreenSize());
    }

    @Test
    public void create_4_brick_render_position_centered_between_rows_cols() {
        var model = buildGridWithDimensions(2, 2);
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();

        assertEquals(10f, gridRender.getX());
        assertEquals(10f, gridRender.getY());
        assertEquals(ObjectSize.widthAndHeight(180, 180), gridRender.onScreenSize());
    }

    @Test
    public void create_9_bricks_render_with_gaps_center_and_size() {
        var model = buildGridWithDimensions(3, 3);
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .withHorizontalGap(10)
                .withVerticalGap(5)
                .render();

        assertEquals(-45f, gridRender.getX());
        assertEquals(-40f, gridRender.getY());
        assertEquals(ObjectSize.widthAndHeight(290, 280), gridRender.onScreenSize());
    }

    @Test
    public void grid_size_1_brick_position_lower_left() {

        var model1Brick = buildGridWithDimensionsAndBricksAtPositions(1, 1, Set.of(GridPosition.ORIGIN));

        var render = BricksGridGdxRender.forModel(model1Brick).render();
        var positionedBricks = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(positionedBricks, render.getX(), render.getY());
    }

    @Test
    public void grid_size_4_brick_positions_at_grid_cells() {

        var model = buildGridWithDimensionsAndBricksAtPositions(2, 2, Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(0, 1),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(1, 1)
        ));

        var render = BricksGridGdxRender.forModel(model).render();
        var landscape = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(landscape, 0f, 0f);
        assertLandscapeHasBrickWithPosition(landscape, 0f, -90f);
        assertLandscapeHasBrickWithPosition(landscape, -90f, 0f);
        assertLandscapeHasBrickWithPosition(landscape, -90f, -90f);
    }

    @Test
    public void grid_screen_offset_bricks_affected() {

        var model = buildGridWithDimensionsAndBricksAtPositions(2, 2, Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(0, 1),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(1, 1)
        ));

        var render = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();
        var landscape = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(landscape, 10f, 10f);
        assertLandscapeHasBrickWithPosition(landscape, 10f, 100f);
        assertLandscapeHasBrickWithPosition(landscape, 100f, 10f);
        assertLandscapeHasBrickWithPosition(landscape, 100f, 100f);
    }

    @Test
    public void grid_vertical_horizontal_gaps_bricks_affected() {

        var model = buildGridWithDimensionsAndBricksAtPositions(2, 2, Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(0, 1),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(1, 1)
        ));

        var render = BricksGridGdxRender.forModel(model)
                .withHorizontalGap(10)
                .withVerticalGap(5)
                .render();
        var landscape = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(landscape, -95f, -92.5f);
        assertLandscapeHasBrickWithPosition(landscape, -95f, 2.5f);
        assertLandscapeHasBrickWithPosition(landscape, 5f, -92.5f);
        assertLandscapeHasBrickWithPosition(landscape, 5f, 2.5f);
    }

    @Test
    public void grid_gaps_and_offset_affecting_bricks() {

        var model = buildGridWithDimensionsAndBricksAtPositions(2, 2, Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(0, 1),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(1, 1)
        ));

        var render = BricksGridGdxRender.forModel(model)
                .withHorizontalGap(10)
                .withVerticalGap(5)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();
        var landscape = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(landscape, 5f, 7.5f);
        assertLandscapeHasBrickWithPosition(landscape, 5f, 102.5f);
        assertLandscapeHasBrickWithPosition(landscape, 105f, 102.5f);
        assertLandscapeHasBrickWithPosition(landscape, 105f, 7.5f);
    }




    private void assertLandscapeHasBrickWithPosition(BricksLandscape landscape, float x, float y) {
        assertTrue(landscape.stream()
                .peek(System.out::println)
                .anyMatch(brick -> brick.getX() == x && brick.getY() == y));
    }

    private BricksGrid buildGridWithDimensionsAndBricksAtPositions(int rows, int cols, Set<GridPosition> positions) {
        var grid = new BricksGrid(GridDimensions.rowsAndCols(rows, cols));
        positions.forEach(pos -> grid.setBrick(pos, new SimpleBrick()));
        return grid;
    }

    private BricksGrid buildGridWithDimensions(int rows, int cols) {
        return buildGridWithDimensionsAndBricksAtPositions(rows, cols, Set.of());
    }
}
