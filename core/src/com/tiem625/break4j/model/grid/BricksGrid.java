package com.tiem625.break4j.model.grid;

import com.tiem625.break4j.model.bricks.SimpleBrick;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

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
        verifyGridPositionNotOutOfBounds(verifiedNotNull(gridPosition));
        bricksInGrid.put(gridPosition, brick);
    }

    public Optional<SimpleBrick> checkBrickAt(GridPosition position) {
        return Optional.of(verifiedNotNull(position))
                .map(bricksInGrid::get);
    }

    public Stream<SimpleBrick> bricks() {
        return bricksInGrid.values().stream();
    }

    public Optional<SimpleBrick> removeBrick(GridPosition position) {
        verifyGridPositionNotOutOfBounds(verifiedNotNull(position));
        return Optional.of(verifiedNotNull(position))
                .map(bricksInGrid::remove);
    }

    private void verifyGridPositionNotOutOfBounds(GridPosition gridPosition) {
        if (gridSize.isOutOfBounds(gridPosition)) {
            throw new IllegalArgumentException(format("position %s is out of bounds for grid of size %s!", gridPosition, gridSize));
        }
    }
}
