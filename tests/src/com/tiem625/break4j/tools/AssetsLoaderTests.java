package com.tiem625.break4j.tools;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tiem625.break4j.testhelp.GdxFilesHeadlessSpy;
import com.tiem625.break4j.testhelp.GdxHeadlessSupport;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(GdxHeadlessSupport.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AssetsLoaderTests {

    @AfterEach
    public void disposeCachedAssets() {
        AssetsLoader.disposeCachedAssets();
    }

    @Test
    public void load_internal_texture_added_to_dispose_cache() {

        Optional<Texture> foundTexture = AssetsLoader.loadInternalDisposable("brick.png", Texture::new);

        Assertions.assertTrue(foundTexture.isPresent());
        Assertions.assertEquals(1, AssetsLoader.getDisposableAssetsSet().size());
        Assertions.assertTrue(AssetsLoader.getDisposableAssetsSet().contains(foundTexture.get()));
    }

    @Test
    public void load_internal_texture_missing_empty_load() {

        Optional<Texture> loadTexture = AssetsLoader.loadInternalDisposable("missing", Texture::new);

        Assertions.assertTrue(loadTexture.isEmpty());
        Assertions.assertEquals(0, AssetsLoader.getDisposableAssetsSet().size());
    }

    @Test
    public void load_internal_texture_cached_load_again_uses_cache() {

        Optional<Texture> foundTexture = AssetsLoader.loadInternalDisposable("brick.png", Texture::new);
        Optional<Texture> foundSameTexture = AssetsLoader.loadInternalDisposable("brick.png", Texture::new);

        Assertions.assertTrue(foundSameTexture.isPresent());
        var headlessCounter = (GdxFilesHeadlessSpy) Gdx.files;
        Assertions.assertEquals(1, headlessCounter.getNumInternalInvocations());
    }
}
