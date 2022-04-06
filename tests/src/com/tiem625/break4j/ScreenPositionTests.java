package com.tiem625.break4j;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void position_is_before_position_with_higher_x() {
        assertTrue(ScreenPosition.at(5, 0).isBefore(ScreenPosition.at(6, 7)));
        assertTrue(ScreenPosition.at(5, 0).isBefore(ScreenPosition.at(6, 0)));
        assertTrue(ScreenPosition.at(5, 0).isBefore(ScreenPosition.at(6, -7)));
    }

    @Test
    public void position_is_after_position_with_lower_x() {
        assertTrue(ScreenPosition.at(5, 0).isAfter(ScreenPosition.at(4, 7)));
        assertTrue(ScreenPosition.at(5, 0).isAfter(ScreenPosition.at(4, 0)));
        assertTrue(ScreenPosition.at(5, 0).isAfter(ScreenPosition.at(4, -7)));
    }

    @Test
    public void position_is_above_position_with_lower_y() {
        assertTrue(ScreenPosition.at(0, 5).isAbove(ScreenPosition.at(6, 4)));
        assertTrue(ScreenPosition.at(0, 5).isAbove(ScreenPosition.at(0, 4)));
        assertTrue(ScreenPosition.at(0, 5).isAbove(ScreenPosition.at(-6, 4)));
    }

    @Test
    public void position_is_below_position_with_higher_y() {
        assertTrue(ScreenPosition.at(0, 5).isBelow(ScreenPosition.at(7, 6)));
        assertTrue(ScreenPosition.at(0, 5).isBelow(ScreenPosition.at(0, 6)));
        assertTrue(ScreenPosition.at(0, 5).isBelow(ScreenPosition.at(-7, 6)));
    }

    @Test
    public void position_mirrored_symmetry_of_origin() {
        assertEquals(ScreenPosition.at(-50, -50), ScreenPosition.at(50, 50).mirrored());
    }

    @Test
    public void position_origin_mirror_itself() {
        assertEquals(ScreenPosition.ORIGIN, ScreenPosition.ORIGIN.mirrored());
    }
}
