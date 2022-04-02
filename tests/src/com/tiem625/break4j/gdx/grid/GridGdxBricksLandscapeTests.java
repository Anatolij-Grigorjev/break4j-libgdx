package com.tiem625.break4j.gdx.grid;

import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;
import com.tiem625.break4j.model.grid.GridPosition;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GridGdxBricksLandscapeTests {


    @Test
    public void create_grid_render_model_has_same_bricks_count_as_landscape() {

        Set<GridPosition> bricksPositions = Set.of(
                GridPosition.atGridOffset(0, 0)
        );
        var gridGdxRender = mockGridGdxRender(bricksPositions);
        assertEquals(bricksPositions.size(), gridGdxRender.getCurrentBricksLandscape().size());
    }


    @Test
    public void create_grid_render_bricks_landscape_have_same_positions() {

        Set<GridPosition> bricksPositions = Set.of(
                GridPosition.atGridOffset(0, 0),
                GridPosition.atGridOffset(1, 0),
                GridPosition.atGridOffset(0, 1)
        );
        var gridGdxRender = mockGridGdxRender(bricksPositions);
        var bricksLandscape = gridGdxRender.getCurrentBricksLandscape();
        var bricksGridPositions = bricksLandscape.stream()
                .map(GridGdxBricksLandscape.BrickGdxInGrid::getGridPosition)
                .collect(Collectors.toSet());
        assertEquals(bricksGridPositions, bricksPositions);
    }


    private BricksGridGdxRender mockGridGdxRender(Set<GridPosition> bricksPositions) {

        var modelGrid = new BricksGrid(GridDimensions.containingAll(bricksPositions));
        bricksPositions.forEach(position -> modelGrid.setBrick(position, new SimpleBrick()));
        return BricksGridGdxRender.forModel(modelGrid).render();
    }
}
