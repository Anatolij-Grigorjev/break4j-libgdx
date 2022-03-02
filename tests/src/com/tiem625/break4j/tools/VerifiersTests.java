package com.tiem625.break4j.tools;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class VerifiersTests {

    @Test
    public void verifier_not_null_arg_null_throws() {

        assertThrows(IllegalArgumentException.class, () -> Verifiers.verifiedNotNull(null));
    }

    @Test
    public void verifier_not_null_arg_present_returns_arg() {

        Object nonNullObject = new Object();
        Object verifierReturn = assertDoesNotThrow(() -> Verifiers.verifiedNotNull(nonNullObject));
        assertEquals(verifierReturn, nonNullObject);
    }
}
