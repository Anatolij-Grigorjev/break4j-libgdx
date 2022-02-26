package com.tiem625.break4j.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class AssetsLoader {

    private final Set<Disposable> disposables;

    public AssetsLoader() {
        disposables = ConcurrentHashMap.newKeySet();
    }

    public <A extends Disposable> Optional<A> loadInternalDisposable(
            String internalPath, Function<FileHandle, A> disposableLoader
    ) {
        var handle = Gdx.files.internal(internalPath);
        if (handle.exists()) {
            A loadedAsset = disposableLoader.apply(handle);
            disposables.add(loadedAsset);
            return Optional.of(loadedAsset);
        } else {
            return Optional.empty();
        }
    }

    public Set<Disposable> getDisposables() {
        return disposables;
    }
}
