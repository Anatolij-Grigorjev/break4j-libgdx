package com.tiem625.break4j;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ObjectSizeTests {

    @Test
    public void size_negative_height_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> ObjectSize.widthAndHeight(100, -90));
    }

    @Test
    public void size_negative_width_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> ObjectSize.widthAndHeight(-100, 90));
    }

    @Test
    public void size_dimension_0_ok() {
        assertDoesNotThrow(() -> ObjectSize.widthAndHeight(0, 88));
    }

    @Test
    public void size_same_dimensions_equal() {
        assertEquals(ObjectSize.widthAndHeight(0, 0), ObjectSize.COLLAPSED);
    }

    @Test
    public void size_scaled_affects_dimensions() {
        assertEquals(ObjectSize.widthAndHeight(100, 100), ObjectSize.widthAndHeight(50, 50).scaledBy(2));
    }

    @Test
    public void size_extended_adds_extension() {
        assertEquals(ObjectSize.widthAndHeight(10, 5), ObjectSize.widthAndHeight(5, 0).extendedBy(5, 5));
    }
}
