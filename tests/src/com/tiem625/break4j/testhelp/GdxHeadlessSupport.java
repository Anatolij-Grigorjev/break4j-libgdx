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
import com.badlogic.gdx.graphics.GL30;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GdxHeadlessSupport implements ApplicationListener, BeforeEachCallback {

    public GdxHeadlessSupport() {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();

        new HeadlessApplication(this, conf);
        Gdx.gl20 = mock(GL20.class);
        Gdx.gl30 = mock(GL30.class);
        Gdx.gl = Gdx.gl20;
        //create shaders for shape renderer
        when(Gdx.gl20.glCreateShader(anyInt())).thenReturn(0);
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
