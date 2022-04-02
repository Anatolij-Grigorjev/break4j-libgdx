package com.tiem625.break4j.gdx.grid;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Logger;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;

import static com.tiem625.break4j.gdx.bricks.BrickGdxRender.BRICK_ONSCREEN_SIZE;
import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class BricksGridGdxRender extends Group {

    private final static Logger logger = new Logger("GRID", Logger.DEBUG);

    private final BricksGrid model;
    private final ScreenPosition centerPosition;
    private final ObjectSize gridScreenSize;
    private final int bricksVerticalGap;
    private final int bricksHorizontalGap;
    private final GridGdxBricksLandscape bricksLandscape;

    private BricksGridGdxRender(BricksGrid model, ScreenPosition centerPosition, int bricksVerticalGap, int bricksHorizontalGap) {

        this.model = model;
        this.centerPosition = centerPosition;
        this.bricksVerticalGap = bricksVerticalGap;
        this.bricksHorizontalGap = bricksHorizontalGap;

        gridScreenSize = calculateGridOnscreenSize(model.dimensions(), bricksHorizontalGap, bricksVerticalGap);
        ScreenPosition gridBottomLeftCornerPosition = calculateGridCornerPosition(gridScreenSize, centerPosition);
        this.setBounds(
                gridBottomLeftCornerPosition.x(), gridBottomLeftCornerPosition.y(),
                gridScreenSize.width(), gridScreenSize.height()
        );

        bricksLandscape = new GridGdxBricksLandscape(this);
    }

    public static BricksGridRendering forModel(BricksGrid model) {
        return new BricksGridRendering(model);
    }

    public GridGdxBricksLandscape getCurrentBricksLandscape() {
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

    public BricksGrid getModel() {
        return model;
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
                numRows * BRICK_ONSCREEN_SIZE.width() + bricksHorizontalGap * (numCols - 1),
                numCols * BRICK_ONSCREEN_SIZE.height() + bricksVerticalGap * (numRows - 1)
        );
    }

    private ScreenPosition calculateGridCornerPosition(ObjectSize gridOnscreenSize, ScreenPosition centerPosition) {

        return centerPosition.offsetBy(ScreenPosition.at(
                gridOnscreenSize.width() / (-2.0f),
                gridOnscreenSize.height() / (-2.0f)
        ));
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
