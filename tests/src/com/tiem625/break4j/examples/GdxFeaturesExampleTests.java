/*******************************************************************************
 Initial version copied from: https://github.com/TomGrill/gdx-testing
 Adapted to JUnit5
 ******************************************************************************/

package com.tiem625.break4j.examples;

import com.badlogic.gdx.Gdx;
import com.tiem625.break4j.GdxHeadlessSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GdxHeadlessSupport.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GdxFeaturesExampleTests {

    @Test
    public void badlogic_file_exits_at_assets_root() {
        Assertions.assertTrue(Gdx.files.internal("badlogic.jpg").exists(), "This test will only pass when the badlogic.jpg file coming with a new project setup has not been deleted.");
    }
}
