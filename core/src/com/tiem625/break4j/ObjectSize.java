package com.tiem625.break4j;

import java.util.Objects;

import static com.tiem625.break4j.tools.Verifiers.verifyNotNegative;

public class ObjectSize {

    public static final ObjectSize COLLAPSED = new ObjectSize(0, 0);

    private final float width;
    private final float height;

    public static ObjectSize widthAndHeight(float width, float height) {
        return new ObjectSize(verifyNotNegative(width), verifyNotNegative(height));
    }

    private ObjectSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectSize that = (ObjectSize) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
