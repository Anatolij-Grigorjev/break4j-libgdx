package com.tiem625.break4j;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tiem625.break4j.gdx.ball.BallGdxRender;
import com.tiem625.break4j.gdx.grid.BricksGridGdxRender;
import com.tiem625.break4j.model.ball.Ball;
import com.tiem625.break4j.model.bricks.BrickSide;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;
import com.tiem625.break4j.model.grid.GridPosition;
import com.tiem625.break4j.tools.AssetsLoader;

import java.util.Optional;

public class Break4jGameLoop extends ApplicationAdapter {

    private final static Logger logger = new Logger("MAIN-LOOP", Logger.DEBUG);

    OrthographicCamera camera;
    SpriteBatch batch;
    BricksGridGdxRender gridGdxRender;
    BallGdxRender ballGdxRender;

    @Override
    public void create() {
        camera = new OrthographicCamera(640, 480);
        camera.setToOrtho(false);
        batch = new SpriteBatch();

        BricksGrid model = new BricksGrid(GridDimensions.rowsAndCols(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model.setBrick(GridPosition.atGridOffset(j, i), new SimpleBrick());
            }
        }
        gridGdxRender = BricksGridGdxRender.forModel(model)
                .centeredAt(ScreenPosition.at(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2))
                .withHorizontalGap(0)
                .withVerticalGap(0)
                .render();
        Ball ballModel = new Ball();
        ballGdxRender = new BallGdxRender(ballModel);
        ballGdxRender.setPosition(50, 50);
        ballModel.addImpulse(new Vector2(20, 20));
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void render() {
        camera.update();
        ballGdxRender.act(Gdx.graphics.getDeltaTime());
        gridGdxRender.getCurrentBricksLandscape().stream()
                .forEach(brickGdxRender -> {
                    Optional<BrickSide> brickSide = ballGdxRender.checkCollisionWith(brickGdxRender.getBrickRender());
                    brickSide.ifPresent(side -> {
                        ballGdxRender.doCollisionWith(brickGdxRender.getBrickRender(), side);
                    });
                });
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        gridGdxRender.draw(batch, 1.0f);
        ballGdxRender.draw(batch, 1.0f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        AssetsLoader.disposeCachedAssets();
    }
}
