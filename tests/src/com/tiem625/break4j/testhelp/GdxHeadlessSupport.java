/*******************************************************************************
 Initial version copied from: https://github.com/TomGrill/gdx-testing
 Adapted to JUnit5
 ******************************************************************************/

package com.tiem625.break4j.testhelp;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.mockito.Mockito.mock;

public class GdxHeadlessSupport implements ApplicationListener, BeforeEachCallback {

    public GdxHeadlessSupport() {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();

        new HeadlessApplication(this, conf);
        Gdx.gl = mock(GL20.class);
        Gdx.files = new GdxFilesHeadlessSpy(Gdx.files);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ((GdxFilesHeadlessSpy) Gdx.files).clearInvocationsCounters();
    }

    @Override
    public void create() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void render() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void dispose() {
    }
}
