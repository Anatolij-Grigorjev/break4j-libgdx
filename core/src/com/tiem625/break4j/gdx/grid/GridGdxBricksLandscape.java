package com.tiem625.break4j.gdx.grid;

import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.bricks.BrickGdxRender;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.grid.GridPosition;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static com.tiem625.break4j.gdx.bricks.BrickGdxRender.BRICK_ONSCREEN_SIZE;

public class GridGdxBricksLandscape {

    private final BricksGridGdxRender gridGdxRender;
    private final Set<BrickGdxInGrid> positionedGridBricks;

    GridGdxBricksLandscape(BricksGridGdxRender gridGdxRender) {
        this.gridGdxRender = gridGdxRender;
        this.positionedGridBricks = ConcurrentHashMap.newKeySet();
        gridGdxRender.getModel().slottedBricks()
                .map(slottedBrick -> renderBrickAtPositionInGridRender(gridGdxRender, slottedBrick.brick, slottedBrick.slot))
                .peek(brickGdxInGrid -> gridGdxRender.addActor(brickGdxInGrid.brickRender))
                .forEach(positionedGridBricks::add);
    }

    public int size() {
        return positionedGridBricks.size();
    }

    public Stream<BrickGdxInGrid> stream() {
        return positionedGridBricks.stream();
    }

    private BrickGdxInGrid renderBrickAtPositionInGridRender(BricksGridGdxRender gridRender, SimpleBrick brick, GridPosition gridPosition) {
        var brickRender = BrickGdxRender.renderModel(brick);

        brickRender.setPosition(
                gridPosition.col() * (BRICK_ONSCREEN_SIZE.width() + gridGdxRender.getHorizontalGap()),
                gridPosition.row() * (BRICK_ONSCREEN_SIZE.height() + gridGdxRender.getVerticalGap())
        );

        return new BrickGdxInGrid(
                brickRender,
                gridPosition,
                brickRender.localPosition().offsetBy(gridRender.lowerLeftCornerPosition())
        );
    }

    public static class BrickGdxInGrid {

        private final BrickGdxRender brickRender;
        private final GridPosition gridPosition;
        private final ScreenPosition screenPosition;

        public BrickGdxInGrid(BrickGdxRender brickRender, GridPosition gridPosition, ScreenPosition screenPosition) {
            this.brickRender = brickRender;
            this.gridPosition = gridPosition;
            this.screenPosition = screenPosition;
        }

        public BrickGdxRender getBrickRender() {
            return brickRender;
        }

        public GridPosition getGridPosition() {
            return gridPosition;
        }

        public ScreenPosition getScreenPosition() {
            return screenPosition;
        }
    }
}
