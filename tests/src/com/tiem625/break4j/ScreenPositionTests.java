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
}
