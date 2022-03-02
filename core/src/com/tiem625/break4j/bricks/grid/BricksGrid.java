package com.tiem625.break4j.bricks.grid;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.bricks.BrickPosition;
import com.tiem625.break4j.bricks.SimpleBrick;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class BricksGrid extends Group {

    private final ScreenPosition screenCoords;
    private final GridDimensions dimensions;

    Map<BrickPosition, SimpleBrick> bricksInGrid;

    public BricksGrid(ScreenPosition gridScreenCoords, GridDimensions dimensions) {

        this.screenCoords = verifiedNotNull(gridScreenCoords);
        this.dimensions = verifiedNotNull(dimensions);

        this.bricksInGrid = new ConcurrentHashMap<>();
    }

    public int getCurrentNumBricks() {
        return getChildren().size;
    }

    public void setBrick(BrickPosition origin, SimpleBrick simpleBrick) {
        addActor(simpleBrick);
    }
}
