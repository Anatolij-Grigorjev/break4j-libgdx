package com.tiem625.break4j.bricks.grid;

import java.util.Objects;

public class GridDimensions {

    public static final GridDimensions COLLAPSED = new GridDimensions(0, 0);
    private final int numRows;
    private final int numCols;

    public static GridDimensions rowsAndCols(int numRows, int numCols) {
        if (numRows < 0 || numCols < 0) {
            throw new IllegalArgumentException("supplied rows or cols num was less than 0!");
        }
        return new GridDimensions(numRows, numCols);
    }

    private GridDimensions(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
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
}
