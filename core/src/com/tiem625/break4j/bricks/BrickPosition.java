package com.tiem625.break4j.bricks;

import java.util.Objects;

/**
 * Brick position, intended to work with some instance of a BricksGrid,
 * these objects help position the Bricks on that Grid
 */
public class BrickPosition {

    public static final BrickPosition ORIGIN = BrickPosition.atGridOffset(0, 0);

    private final int x;
    private final int y;

    public static BrickPosition atGridOffset(int x, int y) {
        return new BrickPosition(x, y);
    }

    private BrickPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrickPosition that = (BrickPosition) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
