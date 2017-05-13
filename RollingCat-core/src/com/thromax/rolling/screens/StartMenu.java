package com.thromax.rolling.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartMenu implements Screen {

	private static Game game;

	private static OrthographicCamera camera;
	private static Viewport view;

	TextButton startB;

	private static Stage stage;

	public StartMenu(Game g) {
		game = g;
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		Skin skin = new Skin(Gdx.files.internal("img/HUD/Skins/orange/skin/uiskin.json"));

		startB = new TextButton("Play", skin);
		startB.setSize(300, 100);
		startB.setPosition((Gdx.graphics.getWidth() / 2 - (startB.getWidth() / 2)), (Gdx.graphics.getHeight() / 2 - startB.getHeight()));

		startB.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				game.setScreen(new Play());
			}
		});

		stage.addActor(startB);

	}

	@Override
	public void show() {
		// Initializes camera
		camera = new OrthographicCamera();
		view = new FillViewport(Gdx.graphics.getWidth() * 5.7f, Gdx.graphics.getHeight() * 5.7f, camera);
		view.apply();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.56f, .91f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void hide() {
		dispose();

	}
}
