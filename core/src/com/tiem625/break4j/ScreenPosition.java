package com.tiem625.break4j;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.tools.Verifiers;

import java.util.Objects;
import java.util.Optional;

public class ScreenPosition {

    public static final ScreenPosition ORIGIN = new ScreenPosition(0, 0);

    private final float x;
    private final float y;

    public static ScreenPosition at(float x, float y) {
        return new ScreenPosition(x, y);
    }

    public static ScreenPosition fromVector(Vector2 position) {
        var present = Verifiers.verifiedNotNull(position);
        return new ScreenPosition(present.x, present.y);
    }

    private ScreenPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public ScreenPosition offsetBy(ScreenPosition offset) {
        return Optional.ofNullable(offset)
                .map(presentOffset -> ScreenPosition.at(x + presentOffset.x, y + presentOffset.y))
                .orElse(this);
    }

    public Vector2 asVector() {
        return new Vector2(x(), y());
    }

    public ScreenPosition offsetBy(ObjectSize size) {
        return Optional.ofNullable(size)
                .map(presentSize -> offsetBy(ScreenPosition.at(size.width(), size.height())))
                .orElse(this);
    }

    public boolean isBefore(ScreenPosition position) {
        return x < position.x;
    }

    public boolean isAfter(ScreenPosition position) {
        return x > position.x;
    }

    public boolean isAbove(ScreenPosition position) {
        return y > position.y;
    }

    public boolean isBelow(ScreenPosition position) {
        return y < position.y;
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

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public ScreenPosition mirrored() {
        return ScreenPosition.at(-x(), -y());
    }
}
