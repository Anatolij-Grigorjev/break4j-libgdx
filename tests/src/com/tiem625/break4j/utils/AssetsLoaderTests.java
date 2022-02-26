package com.tiem625.break4j.utils;


import com.badlogic.gdx.graphics.Texture;
import com.tiem625.break4j.GdxHeadlessSupport;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(GdxHeadlessSupport.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AssetsLoaderTests {

    private AssetsLoader assetsLoader;

    @BeforeEach
    public void setupAssetsLoader() {
        assetsLoader = new AssetsLoader();
    }

    @Test
    public void load_internal_texture_added_to_dispose_cache() {

        Optional<Texture> foundTexture = assetsLoader.loadInternalDisposable("badlogic.jpg", Texture::new);

        Assertions.assertTrue(foundTexture.isPresent());
        Assertions.assertEquals(1, assetsLoader.getDisposables().size());
        Assertions.assertTrue(assetsLoader.getDisposables().contains(foundTexture.get()));
    }

    @Test
    public void load_internal_texture_missing_empty_load() {

        Optional<Texture> loadTexture = assetsLoader.loadInternalDisposable("missing", Texture::new);

        Assertions.assertTrue(loadTexture.isEmpty());
        Assertions.assertEquals(0, assetsLoader.getDisposables().size());
    }
}
