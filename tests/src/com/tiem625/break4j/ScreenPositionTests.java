package com.tiem625.break4j;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ScreenPositionTests {

    @Test
    public void position_negative_valid() {
        assertDoesNotThrow(() -> ScreenPosition.at(-70, 90));
    }

    @Test
    public void position_objects_same_coords_equal() {
        assertEquals(ScreenPosition.at(90, 78), ScreenPosition.at(90, 78));
    }

    @Test
    public void position_offset_by_position_adds_coordinates() {
        assertEquals(ScreenPosition.at(5, 10), ScreenPosition.at(1, 2).offsetBy(ScreenPosition.at(4, 8)));
    }

    @Test
    public void position_offset_by_size_adds_width_and_height() {
        assertEquals(
                ScreenPosition.at(5, 10),
                ScreenPosition.ORIGIN.offsetBy(ObjectSize.widthAndHeight(5, 10))
        );
    }

    @Test
    public void position_offset_by_null_same_position() {
        assertEquals(ScreenPosition.ORIGIN, ScreenPosition.ORIGIN.offsetBy((ScreenPosition) null));
        assertEquals(ScreenPosition.ORIGIN, ScreenPosition.ORIGIN.offsetBy((ObjectSize) null));
    }

}
