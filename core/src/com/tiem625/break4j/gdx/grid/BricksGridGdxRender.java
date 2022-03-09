package com.tiem625.break4j.gdx.grid;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.model.grid.BricksGrid;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class BricksGridGdxRender extends Group {

    private final BricksGrid model;

    private BricksGridGdxRender(BricksGrid model, ScreenPosition position) {

        this.model = model;
        this.setPosition(position.getX(), position.getY());
    }

    public static BricksGridRendering forModel(BricksGrid model) {
        return new BricksGridRendering(model);
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

        public BricksGridGdxRender render() {
            throw new UnsupportedOperationException("TODO");
        }

        public BricksGridRendering atPosition(ScreenPosition screenPosition) {
            this.gridPosition = screenPosition;
            return this;
        }
    }
}
