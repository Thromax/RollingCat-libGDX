package com.thromax.rolling.screens.menu;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.Main;
import com.thromax.rolling.entities.misc.LoadQueue;
import com.thromax.rolling.screens.LoadingScreen;

public class ControlsScreen implements Screen {

	// Elements:
	private Label Title;
	private ButtonBack back;

	// Elements Text:
	private String stringTitle = "Controls Settings";

	// Skin and Stage:
	private Skin skin;
	private Stage stage;

	// Padding:
	private float padding = 10.0f;

	private Main main;
	private Game game;

	public ControlsScreen(Main main, Game game) {
		this.main = main;
		this.game = game;
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
	public void render(float arg0) {
		Gdx.gl.glClearColor(.56f, .91f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int arg0, int arg1) {
		

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("img/HUD/Skins/orange/skin/uiskin.json"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		initElements();
	}

	private void initElements() {

		Title = new Label(stringTitle, skin);
		Title.setPosition(GameConstants.SCREEN_WIDTH / 2 - Title.getWidth() / 2,
				GameConstants.SCREEN_HEIGHT - Title.getHeight() - padding);

		// back:
		back = new ButtonBack();
		back.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				game.setScreen(new LoadingScreen(main, new SettingScreen(main, game), SettingScreen.loadQueue()));
			}
		});

		stage.addActor(Title);
		stage.addActor(back);
	}

	public static ArrayList<LoadQueue> loadQueue() {
		ArrayList<LoadQueue> list = new ArrayList<LoadQueue>();
		list.add(new LoadQueue("img/HUD/Skins/orange/skin/uiskin.json", Skin.class));
		return list;
	}

}