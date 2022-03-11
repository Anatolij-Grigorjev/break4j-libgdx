package com.tiem625.break4j.tools.objectId;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OIdTests {

    @Test
    public void oid_long_allowed() {
        assertDoesNotThrow(() -> OId.of(7L));
    }

    @Test
    public void oid_same_number_equal() {
        assertEquals(OId.of(5L), OId.of(5L));
    }
}
