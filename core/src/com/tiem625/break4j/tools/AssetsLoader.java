package com.tiem625.break4j.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Mechanism to load game assets in a centralized way for common logging,
 * caching and disposal
 */
public class AssetsLoader {

    private final static Logger logger = new Logger("ASSETS-LOADER", Logger.DEBUG);


    private final Map<String, Disposable> loadedDisposableAssetsCache;

    public AssetsLoader() {
        loadedDisposableAssetsCache = new ConcurrentHashMap<>();
    }

    public <A extends Disposable> Optional<A> loadInternalDisposable(
            String internalPath, Function<FileHandle, A> disposableLoader
    ) {
        if (loadedDisposableAssetsCache.containsKey(internalPath)) {
            logger.debug("Looking up loaded asset at internal path " + internalPath);
            return (Optional<A>) Optional.of(loadedDisposableAssetsCache.get(internalPath));
        }
        var handle = Gdx.files.internal(internalPath);
        if (handle.exists()) {
            logger.info("Loading new asset at internal path " + internalPath);
            A loadedAsset = disposableLoader.apply(handle);
            loadedDisposableAssetsCache.putIfAbsent(internalPath, loadedAsset);
            return Optional.of(loadedAsset);
        } else {
            logger.error("Asset not found at internal path " + internalPath);
            return Optional.empty();
        }
    }

    public Set<Disposable> getDisposableAssetsSet() {
        return new HashSet<>(loadedDisposableAssetsCache.values());
    }
}
