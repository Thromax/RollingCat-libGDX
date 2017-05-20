package com.thromax.rolling.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.thromax.rolling.LoadQueue;
import com.thromax.rolling.Main;
import com.thromax.rolling.entities.misc.LoadingCat;

public class LoadingScreen implements Screen {

	private final Main app;
	private final Screen screen;
	private final ArrayList<LoadQueue> loadQueue;

	private float progress;

	private Batch batch;
	private LoadingCat loadCat;
	private Label loadingText;

	public LoadingScreen(final Main main, final Screen screen, final ArrayList<LoadQueue> arrayList) {
		this.app = main;
		this.screen = screen;
		this.loadQueue = arrayList;
	}

	// Loads the class's assets
	private void queueAssets() {
		for (int i = 0; i < loadQueue.size(); i++) {
			try {
				app.assets.load(loadQueue.get(i).file, loadQueue.get(i).type);
			} catch (com.badlogic.gdx.utils.GdxRuntimeException e) {
			}
		}
	}

	@Override
	public void show() {
		// Initialization
		batch = new SpriteBatch();
		Skin skin = new Skin(Gdx.files.internal("img/HUD/Skins/orange/skin/uiskin.json"));
		loadingText = new Label("L O A D I N G . . .", skin, "title");

		// Size and position settings
		loadCat = new LoadingCat(new Sprite());
		loadCat.setScale(15);
		loadCat.setPosition((Gdx.graphics.getWidth() - loadCat.getWidth() * loadCat.getScaleX() - 50), 50);

		loadingText.setPosition((loadCat.getX() - loadingText.getWidth() - 50),
				(loadCat.getY() + ((loadCat.getHeight() * loadCat.getScaleX()) / 2)
						- loadingText.getHeight() * loadingText.getScaleX()));
		this.progress = 0f;

		// Load class's assets
		queueAssets();
	}

	private void update(float delta) {
		progress = MathUtils.lerp(progress, app.assets.getProgress(), .1f);
		if (app.assets.update() && progress >= app.assets.getProgress() - .01f) {
			app.setScreen(screen);
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		loadCat.draw(batch);
		loadingText.draw(batch, 1f);
		batch.end();

		update(delta);
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

}
