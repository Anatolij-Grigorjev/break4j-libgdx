package com.tiem625.break4j.gdx.grid;

import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.bricks.BrickGdxRender;
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
        var brickSize = BrickGdxRender.BRICK_ONSCREEN_SIZE;

        assertEquals(100f - brickSize.width() / 2, gridRender.getX());
        assertEquals(100f - brickSize.height() / 2, gridRender.getY());
        assertEquals(brickSize, gridRender.onScreenSize());
        assertEquals(brickSize.width(), gridRender.getWidth());
        assertEquals(brickSize.height(), gridRender.getHeight());
    }

    @Test
    public void create_4_brick_render_position_centered_between_rows_cols() {
        var model = buildGridWithDimensions(2, 2);
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();
        var brickSize = BrickGdxRender.BRICK_ONSCREEN_SIZE;

        assertEquals(100f - brickSize.width(), gridRender.getX());
        assertEquals(100f - brickSize.height(), gridRender.getY());
        ObjectSize size2Bricks = brickSize.scaledBy(2);
        assertEquals(size2Bricks, gridRender.onScreenSize());
        assertEquals(size2Bricks.width(), gridRender.getWidth());
        assertEquals(size2Bricks.height(), gridRender.getHeight());
    }

    @Test
    public void create_9_bricks_render_with_gaps_center_and_size() {
        var model = buildGridWithDimensions(3, 3);
        var gridRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .withHorizontalGap(10)
                .withVerticalGap(5)
                .render();
        var brickSize = BrickGdxRender.BRICK_ONSCREEN_SIZE;

        assertEquals(100f - brickSize.scaledBy(1.5f).width() - gridRender.getHorizontalGap(), gridRender.getX());
        assertEquals(100f - brickSize.scaledBy(1.5f).height() - gridRender.getVerticalGap(), gridRender.getY());
        assertEquals(brickSize.scaledBy(3).extendedBy(2 * 10, 2 * 5), gridRender.onScreenSize());
        assertEquals(gridRender.onScreenSize().width(), gridRender.getWidth());
        assertEquals(gridRender.onScreenSize().height(), gridRender.getHeight());
    }

    @Test
    public void grid_size_1_brick_position_lower_left() {

        var model1Brick = buildGridWithDimensionsAndBricksAtPositions(1, 1, Set.of(GridPosition.ORIGIN));

        var render = BricksGridGdxRender.forModel(model1Brick).render();
        var positionedBricks = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(positionedBricks, 0, 0);
    }

    @Test
    public void grid_size_4_brick_positions_at_grid_cells() {

        var model = buildGridWithDimensionsAndBricksAtPositions(2, 2, Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(0, 1),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(1, 1)
        ));
        var brickSize = BrickGdxRender.BRICK_ONSCREEN_SIZE;

        var render = BricksGridGdxRender.forModel(model).render();
        var landscape = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(landscape, 0f, 0f);
        assertLandscapeHasBrickWithPosition(landscape, 0f, brickSize.height());
        assertLandscapeHasBrickWithPosition(landscape, brickSize.width(), 0f);
        assertLandscapeHasBrickWithPosition(landscape, brickSize.width(), brickSize.height());
    }

    @Test
    public void grid_screen_offset_bricks_not_affected() {

        var model = buildGridWithDimensionsAndBricksAtPositions(2, 2, Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(0, 1),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(1, 1)
        ));
        var brickSize = BrickGdxRender.BRICK_ONSCREEN_SIZE;

        var render = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(100, 100))
                .render();
        var landscape = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(landscape, 0f, 0f);
        assertLandscapeHasBrickWithPosition(landscape, 0f, brickSize.height());
        assertLandscapeHasBrickWithPosition(landscape, brickSize.width(), 0f);
        assertLandscapeHasBrickWithPosition(landscape, brickSize.width(), brickSize.height());
    }

    @Test
    public void grid_vertical_horizontal_gaps_bricks_affected() {

        var model = buildGridWithDimensionsAndBricksAtPositions(2, 2, Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(0, 1),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(1, 1)
        ));
        var brickSize = BrickGdxRender.BRICK_ONSCREEN_SIZE;

        var render = BricksGridGdxRender.forModel(model)
                .withHorizontalGap(10)
                .withVerticalGap(5)
                .render();
        var landscape = render.getCurrentBricksLandscape();

        assertLandscapeHasBrickWithPosition(landscape, brickSize.width() + 10, brickSize.height() + 5);
        assertLandscapeHasBrickWithPosition(landscape, brickSize.width() + 10, 0);
        assertLandscapeHasBrickWithPosition(landscape, 0, brickSize.height() + 5);
        assertLandscapeHasBrickWithPosition(landscape, 0, 0);
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
