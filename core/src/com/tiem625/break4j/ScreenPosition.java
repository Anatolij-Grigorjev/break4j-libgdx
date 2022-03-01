package com.tiem625.break4j;

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
}
