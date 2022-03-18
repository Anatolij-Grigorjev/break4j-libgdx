package com.tiem625.break4j.gdx.grid;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.bricks.BrickGdxRender;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;
import com.tiem625.break4j.model.grid.GridPosition;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static com.tiem625.break4j.gdx.bricks.BrickGdxRender.BRICK_ONSCREEN_SIZE;
import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class BricksGridGdxRender extends Group {

    private final BricksGrid model;
    private final ScreenPosition centerPosition;
    private final ObjectSize gridScreenSize;
    private final int bricksVerticalGap;
    private final int bricksHorizontalGap;
    private final BricksLandscape bricksLandscape;

    private BricksGridGdxRender(BricksGrid model, ScreenPosition centerPosition, int bricksVerticalGap, int bricksHorizontalGap) {

        this.model = model;
        this.centerPosition = centerPosition;
        this.bricksVerticalGap = bricksVerticalGap;
        this.bricksHorizontalGap = bricksHorizontalGap;

        gridScreenSize = calculateGridOnscreenSize(model.dimensions(), bricksHorizontalGap, bricksVerticalGap);
        ScreenPosition gridBottomLeftCornerPosition = calculateGridCornerPosition(gridScreenSize, centerPosition);
        this.setWidth(gridScreenSize.getWidth());
        this.setHeight(gridScreenSize.getHeight());
        this.setPosition(gridBottomLeftCornerPosition.getX(), gridBottomLeftCornerPosition.getY());

        bricksLandscape = new BricksLandscape();
    }

    public static BricksGridRendering forModel(BricksGrid model) {
        return new BricksGridRendering(model);
    }

    public BricksLandscape getCurrentBricksLandscape() {
        return bricksLandscape;
    }

    public int getVerticalGap() {
        return bricksVerticalGap;
    }

    public int getHorizontalGap() {
        return bricksHorizontalGap;
    }

    public ObjectSize onScreenSize() {
        return gridScreenSize;
    }

    public ScreenPosition onScreenCenter() {
        return centerPosition;
    }

    public ScreenPosition lowerLeftCornerPosition() {
        return ScreenPosition.at(getX(), getY());
    }

    private ObjectSize calculateGridOnscreenSize(GridDimensions gridDimensions, int bricksHorizontalGap, int bricksVerticalGap) {
        if (gridDimensions.isCollapsed()) {
            return ObjectSize.COLLAPSED;
        }

        var numRows = gridDimensions.rows();
        var numCols = gridDimensions.cols();

        return ObjectSize.widthAndHeight(
                numRows * BRICK_ONSCREEN_SIZE.getWidth() + bricksHorizontalGap * (numCols - 1),
                numCols * BRICK_ONSCREEN_SIZE.getHeight() + bricksVerticalGap * (numRows - 1)
        );
    }

    private ScreenPosition calculateGridCornerPosition(ObjectSize gridOnscreenSize, ScreenPosition centerPosition) {

        return centerPosition.offsetBy(ScreenPosition.at(
                gridOnscreenSize.getWidth() / (-2.0f),
                gridOnscreenSize.getHeight() / (-2.0f)
        ));
    }

    public class BricksLandscape {

        private final Set<BrickGdxRender> positionedGridBricks;

        private BricksLandscape() {
            this.positionedGridBricks = ConcurrentHashMap.newKeySet();
            model.slottedBricks()
                    .map(slottedBrick -> renderBrickAtPositionInGridRender(slottedBrick.brick, slottedBrick.slot))
                    .peek(BricksGridGdxRender.this::addActor)
                    .forEach(positionedGridBricks::add);
        }

        public int size() {
            return positionedGridBricks.size();
        }

        public Stream<BrickGdxRender> stream() {
            return positionedGridBricks.stream();
        }

        private BrickGdxRender renderBrickAtPositionInGridRender(SimpleBrick brick, GridPosition gridPosition) {
            var brickRender = BrickGdxRender.renderModel(brick);

            brickRender.setPosition(
                    gridPosition.col() * (BRICK_ONSCREEN_SIZE.getWidth() + bricksHorizontalGap),
                    gridPosition.row() * (BRICK_ONSCREEN_SIZE.getHeight() + bricksVerticalGap)
            );
            return brickRender;
        }
    }

    public static class BricksGridRendering {

        private final BricksGrid model;
        private ScreenPosition gridPosition;
        private int bricksVerticalGap;
        private int bricksHorizontalGap;

        private BricksGridRendering(BricksGrid model) {
            this.model = verifiedNotNull(model);
            this.gridPosition = ScreenPosition.ORIGIN;
            this.bricksVerticalGap = 0;
            this.bricksHorizontalGap = 0;
        }

        public BricksGridRendering centeredAt(ScreenPosition screenPosition) {
            this.gridPosition = screenPosition;
            return this;
        }

        public BricksGridRendering withVerticalGap(int gap) {
            this.bricksVerticalGap = gap;
            return this;
        }

        public BricksGridRendering withHorizontalGap(int gap) {
            this.bricksHorizontalGap = gap;
            return this;
        }

        public BricksGridGdxRender render() {
            return new BricksGridGdxRender(model, gridPosition, bricksVerticalGap, bricksHorizontalGap);
        }
    }
}
