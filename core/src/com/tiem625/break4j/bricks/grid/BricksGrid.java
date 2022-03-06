package com.tiem625.break4j.bricks.grid;

import com.tiem625.break4j.bricks.GridPosition;
import com.tiem625.break4j.bricks.SimpleBrick;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;
import static java.lang.String.format;

public class BricksGrid {

    private final GridDimensions gridSize;
    private final Map<GridPosition, SimpleBrick> bricksInGrid;

    public BricksGrid(GridDimensions gridSize) {

        this.gridSize = verifiedNotNull(gridSize);
        this.bricksInGrid = new ConcurrentHashMap<>();
    }

    public int getCurrentNumBricks() {
        return bricksInGrid.size();
    }

    public void setBrick(GridPosition gridPosition, SimpleBrick brick) {
        if (gridSize.isOutOfBounds(gridPosition)) {
            throw new IllegalArgumentException(format("position %s is out of bounds for grid of size %s!", gridPosition, gridSize));
        }
        bricksInGrid.put(gridPosition, brick);
    }

    public Optional<SimpleBrick> checkBrickAt(GridPosition position) {
        return Optional.ofNullable(bricksInGrid.get(position));
    }
}
