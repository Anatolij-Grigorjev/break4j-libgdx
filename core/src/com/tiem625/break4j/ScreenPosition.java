package com.tiem625.break4j;

import java.util.Objects;
import java.util.Optional;

public class ScreenPosition {

    public static final ScreenPosition ORIGIN = new ScreenPosition(0, 0);

    private final float x;
    private final float y;

    public static ScreenPosition at(float x, float y) {
        return new ScreenPosition(x, y);
    }

    private ScreenPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ScreenPosition offsetBy(ScreenPosition offset) {
        return Optional.ofNullable(offset)
                .map(presentOffset -> ScreenPosition.at(x + presentOffset.x, y + presentOffset.y))
                .orElse(this);
    }

    public ScreenPosition offsetBy(ObjectSize size) {
        return Optional.ofNullable(size)
                .map(presentSize -> offsetBy(ScreenPosition.at(size.width(), size.height())))
                .orElse(this);
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
