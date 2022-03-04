package com.tiem625.break4j;

import java.util.Objects;

import static com.tiem625.break4j.tools.Verifiers.verifyNotNegative;

public class ObjectSize {

    public static final ObjectSize COLLAPSED = new ObjectSize(0, 0);

    private final int width;
    private final int height;

    public static ObjectSize widthAndHeight(int width, int height) {
        return new ObjectSize(verifyNotNegative(width), verifyNotNegative(height));
    }

    private ObjectSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
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
