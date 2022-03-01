package com.tiem625.break4j.bricks.grid;

public class GridDimensions {

    public static final GridDimensions COLLAPSED = new GridDimensions(0, 0);
    private final int numRows;
    private final int numCols;

    public static GridDimensions rowsAndCols(int numRows, int numCols) {
        return null;
    }

    private GridDimensions(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }
}
