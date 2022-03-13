package com.tiem625.break4j.gdx.grid;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.bricks.BrickGdxRender;
import com.tiem625.break4j.model.grid.BricksGrid;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class BricksGridGdxRender extends Group {

    private final BricksGrid model;
    private final ScreenPosition centerPosition;
    private final ObjectSize gridScreenSize;
    private final int bricksVerticalGap;
    private final int bricksHorizontalGap;

    private BricksGridGdxRender(BricksGrid model, ScreenPosition centerPosition, int bricksVerticalGap, int bricksHorizontalGap) {

        this.model = model;
        this.centerPosition = centerPosition;
        this.bricksVerticalGap = bricksVerticalGap;
        this.bricksHorizontalGap = bricksHorizontalGap;

        gridScreenSize = ObjectSize.COLLAPSED;
        this.setPosition(centerPosition.getX(), centerPosition.getY());
    }

    public static BricksGridRendering forModel(BricksGrid model) {
        return new BricksGridRendering(model);
    }

    public BricksLandscape getCurrentBricksLandscape() {
        return new BricksLandscape();
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

    public class BricksLandscape {

        private final Set<BrickGdxRender> positionedGridBricks;

        private BricksLandscape() {
            this.positionedGridBricks = ConcurrentHashMap.newKeySet();
            model.bricks()
                    .map(BrickGdxRender::renderModel)
                    .forEach(positionedGridBricks::add);
        }

        public int size() {
            return positionedGridBricks.size();
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
