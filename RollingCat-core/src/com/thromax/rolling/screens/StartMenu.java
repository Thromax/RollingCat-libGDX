package com.thromax.rolling.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thromax.rolling.LoadQueue;
import com.thromax.rolling.Main;
import com.thromax.rolling.entities.misc.LuckyCat;

public class StartMenu implements Screen {

	private Game game;

	private OrthographicCamera camera;
	private Viewport view;

	TextButton startB, exitB;

	private Stage stage;

	private LuckyCat luckyCat;

	public StartMenu(final Main main, final Game g) {
		game = g;
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		Skin skin = new Skin(Gdx.files.internal("img/HUD/Skins/orange/skin/uiskin.json"));

		// Play Button
		startB = new TextButton("Start", skin);
		startB.setSize(300, 100);
		startB.setPosition((Gdx.graphics.getWidth() / 2 - (startB.getWidth() / 2)),
				(Gdx.graphics.getHeight() / 2 - startB.getHeight()));

		startB.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				game.setScreen(new LoadingScreen(main, new Play(), Play.loadQueue()));
			}
		});

		stage.addActor(startB);

		// Exit Button
		exitB = new TextButton("Exit", skin);
		exitB.setSize(300, 100);
		exitB.setPosition(startB.getX(), (startB.getY() - startB.getHeight() - 10));
		exitB.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				Gdx.app.exit();
			}

		});
		stage.addActor(exitB);

	}

	@Override
	public void show() {

		luckyCat = new LuckyCat(new Sprite());
		luckyCat.setScale(5);
		luckyCat.setPosition(0, 150);

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

		stage.getBatch().begin();
		luckyCat.draw(stage.getBatch());
		stage.getBatch().end();

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

	// Returns the requirements needed to load this class
	public static ArrayList<LoadQueue> loadQueue() {
		ArrayList<LoadQueue> list = new ArrayList<LoadQueue>();

		list.add(new LoadQueue("img/HUD/Skins/orange/skin/uiskin.json", Skin.class));

		for (LoadQueue lq : LuckyCat.loadQueue()) {
			list.add(lq);
		}
		return list;
	}

	@Override
	public void hide() {
		dispose();

	}
}
