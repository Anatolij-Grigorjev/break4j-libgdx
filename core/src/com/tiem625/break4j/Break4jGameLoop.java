package com.tiem625.break4j;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tiem625.break4j.gdx.grid.BricksGridGdxRender;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.model.grid.BricksGrid;
import com.tiem625.break4j.model.grid.GridDimensions;
import com.tiem625.break4j.model.grid.GridPosition;
import com.tiem625.break4j.tools.AssetsLoader;

public class Break4jGameLoop extends ApplicationAdapter {
	SpriteBatch batch;
	BricksGridGdxRender gridGdxRender;
	
	@Override
	public void create () {
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
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		gridGdxRender.draw(batch, 1.0f);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		AssetsLoader.disposeCachedAssets();
	}
}
