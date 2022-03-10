package com.tiem625.break4j;

import java.util.Objects;

public class ScreenPosition {

    public static final ScreenPosition ORIGIN = new ScreenPosition(0, 0);

    private final int x;
    private final int y;

    public static ScreenPosition at(int x, int y) {
        return new ScreenPosition(x, y);
    }

    private ScreenPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ScreenPosition offsetBy(ScreenPosition offset) {
        return ScreenPosition.at(x + offset.x, y + offset.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenPosition that = (ScreenPosition) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
