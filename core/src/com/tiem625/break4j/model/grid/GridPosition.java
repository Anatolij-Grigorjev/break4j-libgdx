package com.tiem625.break4j.model.grid;

import java.util.Objects;

import static com.tiem625.break4j.tools.Verifiers.verifyNotNegative;
import static java.lang.String.format;

/**
 * Brick position, intended to work with some instance of a BricksGrid,
 * these objects help position the Bricks on that Grid
 */
public class GridPosition {

    public static final GridPosition ORIGIN = GridPosition.atGridOffset(0, 0);

    private final int row;
    private final int col;

    public static GridPosition atGridOffset(int x, int y) {
        return new GridPosition(verifyNotNegative(x), verifyNotNegative(y));
    }

    private GridPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridPosition that = (GridPosition) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return format("<%s:%s>", row, col);
    }
}
