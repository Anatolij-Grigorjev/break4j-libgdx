package com.tiem625.break4j.tools;


/**
 * Entity that has assets which require loading should implement this interface
 * to gain access to the main game {@link AssetsLoader} for adding those assets in at loadtime
 */
public interface HasAssets {

    default void loadEntityAssets(AssetsLoader loader) {}
}
