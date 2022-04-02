package com.tiem625.break4j.model.grid;

import java.util.Objects;
import java.util.Set;

import static com.tiem625.break4j.tools.Verifiers.verifyNotNegative;
import static java.lang.String.format;

public class GridDimensions {

    public static final GridDimensions COLLAPSED = new GridDimensions(0, 0);
    private final int numRows;
    private final int numCols;

    public static GridDimensions rowsAndCols(int numRows, int numCols) {
        return new GridDimensions(verifyNotNegative(numRows), verifyNotNegative(numCols));
    }

    private GridDimensions(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public static GridDimensions containingAll(Set<GridPosition> gridPositions) {
        if (gridPositions == null || gridPositions.isEmpty()) {
            return COLLAPSED;
        }
        return new GridDimensions(
            gridPositions.stream().mapToInt(GridPosition::row).max().orElse(-1) + 1,
            gridPositions.stream().mapToInt(GridPosition::col).max().orElse(-1) + 1
        );
    }

    public int rows() {
        return numRows;
    }

    public int cols() {
        return numCols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridDimensions that = (GridDimensions) o;
        return numRows == that.numRows && numCols == that.numCols;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numRows, numCols);
    }

    @Override
    public String toString() {
        return format("[%s x %s]", numRows, numCols);
    }

    public boolean isOutOfBounds(GridPosition gridPosition) {
        return gridPosition.row() >= numRows || gridPosition.col() >= numCols;
    }

    public boolean isCollapsed() {
        return cols() <= 0 || rows() <= 0;
    }
}
