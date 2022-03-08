package com.tiem625.break4j.gdx.grid;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.model.grid.BricksGrid;

public class BricksGridGdxRender extends Group {

    private final BricksGrid model;

    public BricksGridGdxRender(BricksGrid model, ScreenPosition position) {

        this.model = model;
        this.setPosition(position.getX(), position.getY());
    }
}
