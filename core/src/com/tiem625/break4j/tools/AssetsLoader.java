package com.tiem625.break4j.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class AssetsLoader {

    private final Map<String, Disposable> loadedDisposableAssetsCache;

    public AssetsLoader() {
        loadedDisposableAssetsCache = new ConcurrentHashMap<>();
    }

    public <A extends Disposable> Optional<A> loadInternalDisposable(
            String internalPath, Function<FileHandle, A> disposableLoader
    ) {
        if (loadedDisposableAssetsCache.containsKey(internalPath)) {
            return (Optional<A>) Optional.of(loadedDisposableAssetsCache.get(internalPath));
        }
        var handle = Gdx.files.internal(internalPath);
        if (handle.exists()) {
            A loadedAsset = disposableLoader.apply(handle);
            loadedDisposableAssetsCache.putIfAbsent(internalPath, loadedAsset);
            return Optional.of(loadedAsset);
        } else {
            return Optional.empty();
        }
    }

    public Set<Disposable> getDisposableAssetsSet() {
        return new HashSet<>(loadedDisposableAssetsCache.values());
    }
}
