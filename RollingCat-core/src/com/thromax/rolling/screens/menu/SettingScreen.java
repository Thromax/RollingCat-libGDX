package com.thromax.rolling.screens.menu;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.Main;
import com.thromax.rolling.entities.misc.LoadQueue;
import com.thromax.rolling.screens.LoadingScreen;

public class SettingScreen implements Screen {

	// Back
	private ButtonBack back;

	// Category:
	private String[] labels = { "Sound", "Video", "Controls" };

	// Elements Text:
	private String stringAcept = "Apply";

	// Skin and stage
	Skin skin;
	Stage stage;

	// Separate value:
	private final float separateCategory = 100;
	private final float leveler = 30;

	// Input handler of the Buttons:
	private Main main;
	private Game game;

	public SettingScreen(Main main, Game game) {
		this.game = game;
		this.main = main;
	}

	@Override
	public void dispose() {

		stage.dispose();
		skin.dispose();
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

		// Category of Settings
		for (int i = 0; i < labels.length; i++) {
			final int j = i;
			TextButton button = new TextButton(labels[i], skin);
			button.setSize(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
			button.setPosition(GameConstants.SCREEN_WIDTH / 2 - GameConstants.BUTTON_WIDTH / 2,
					GameConstants.SCREEN_HEIGHT / 2 + GameConstants.BUTTON_HEIGHT - separateCategory * i - leveler);
			button.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button) {
					switch (j) {
					case 0:
						game.setScreen(new LoadingScreen(main, new SoundScreen(main, game), SoundScreen.loadQueue()));
						break;
					case 1:
						game.setScreen(new LoadingScreen(main, new VideoScreen(main, game), VideoScreen.loadQueue()));
						break;
					case 2:
						game.setScreen(
								new LoadingScreen(main, new ControlsScreen(main, game), ControlsScreen.loadQueue()));
						break;
					default:
						break;
					}

				}
			});
			stage.addActor(button);
		}

		// Button Back and Accept
		back = new ButtonBack();

		back.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				game.setScreen(new LoadingScreen(main, new MainMenu(game, main), MainMenu.loadQueue()));
			}
		});

		stage.addActor(back);

	}

	public static ArrayList<LoadQueue> loadQueue() {
		ArrayList<LoadQueue> list = new ArrayList<LoadQueue>();
		list.add(new LoadQueue("img/HUD/Skins/orange/skin/uiskin.json", Skin.class));
		return list;
	}

}
