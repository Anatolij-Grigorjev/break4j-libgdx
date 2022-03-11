package com.tiem625.break4j.tools.objectId;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OIdSequenceTests {

    @Test
    public void sequence_provides_incremental_id() {

        OId id1 = OIdSequence.nextId();
        OId id2 = OIdSequence.nextId();

        assertNotEquals(id1, id2);
    }

    @Test
    public void sequence_always_different_ids() {

        int streamSize = 5000;

        Set<OId> ids = Stream.generate(OIdSequence::nextId)
                .limit(streamSize)
                .collect(Collectors.toSet());

        assertEquals(streamSize, ids.size());
    }

}
