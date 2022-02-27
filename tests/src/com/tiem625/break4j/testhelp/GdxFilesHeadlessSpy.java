package com.tiem625.break4j.testhelp;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.files.FileHandle;

public class GdxFilesHeadlessSpy implements Files {

    HeadlessFiles files;

    int internalInvocations = 0;
    int externalInvocations = 0;

    public GdxFilesHeadlessSpy(Files headlessFiles) {
        this.files = (HeadlessFiles) headlessFiles;
    }

    @Override
    public FileHandle getFileHandle(String path, FileType type) {
        return files.getFileHandle(path, type);
    }

    @Override
    public FileHandle classpath(String path) {
        return files.classpath(path);
    }

    @Override
    public FileHandle internal(String path) {
        internalInvocations++;
        return files.internal(path);
    }

    @Override
    public FileHandle external(String path) {
        externalInvocations++;
        return files.external(path);
    }

    @Override
    public FileHandle absolute(String path) {
        return files.absolute(path);
    }

    @Override
    public FileHandle local(String path) {
        return files.local(path);
    }

    @Override
    public String getExternalStoragePath() {
        return files.getExternalStoragePath();
    }

    @Override
    public boolean isExternalStorageAvailable() {
        return files.isExternalStorageAvailable();
    }

    @Override
    public String getLocalStoragePath() {
        return files.getLocalStoragePath();
    }

    @Override
    public boolean isLocalStorageAvailable() {
        return files.isLocalStorageAvailable();
    }

    public void clearInvocationsCounters() {
        internalInvocations = 0;
        externalInvocations = 0;
    }

    public int getNumInternalInvocations() {
        return internalInvocations;
    }

    public int getNumExternalInvocations() {
        return externalInvocations;
    }
}
