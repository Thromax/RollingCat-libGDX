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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.Main;
import com.thromax.rolling.entities.misc.LoadQueue;
import com.thromax.rolling.screens.LoadingScreen;

public class SoundScreen implements Screen {

	private ButtonBack back;
	// Slider
	private Slider music;
	private Slider effects;
	// Labels for the Sliders
	private Label musicLabel;
	private Label effectsLabel;
	// Strings
	private String stringVol = "Music";
	private String stringSound = "Effects";
	// Size
	private final static float sliderWidth = 300;
	private final static float sliderHeight = 30;
	// Label Size on Width
	private final static float labelSize = 2;
	// Skin and Stage
	private Skin skin;
	private Stage stage;
	Main main;
	Game game;

	public SoundScreen(Main main, Game game) {
		this.main = main;
		this.game = game;
	}

	@Override
	public void dispose() {
		
		skin.dispose();
		stage.dispose();
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

		// Sliders and Its labels:
		music = new Slider(0, 100, 0.1f, false, skin);
		effects = new Slider(0, 100, 0.1f, false, skin);

		musicLabel = new Label(stringVol, skin);
		effectsLabel = new Label(stringSound, skin);

		musicLabel.setFontScale(labelSize);
		musicLabel.setPosition(GameConstants.SCREEN_WIDTH / 2 - sliderWidth,
				GameConstants.SCREEN_HEIGHT / 2 + musicLabel.getHeight() / 2);
		music.setPosition(GameConstants.SCREEN_WIDTH / 2 - sliderWidth / 2, GameConstants.SCREEN_HEIGHT / 2);
		music.setSize(sliderWidth, sliderHeight);

		effectsLabel.setFontScale(labelSize);
		effectsLabel.setPosition(GameConstants.SCREEN_WIDTH / 2 - sliderWidth,
				GameConstants.SCREEN_HEIGHT / 2 - sliderHeight - GameConstants.PADDING + effectsLabel.getHeight() / 2);
		effects.setPosition(GameConstants.SCREEN_WIDTH / 2 - sliderWidth / 2,
				GameConstants.SCREEN_HEIGHT / 2 - sliderHeight - GameConstants.PADDING);
		effects.setSize(sliderWidth, sliderHeight);

		// Back:
		back = new ButtonBack();
		back.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				game.setScreen(new LoadingScreen(main, new SettingScreen(main, game), SettingScreen.loadQueue()));
			}
		});
		stage.addActor(back);
		stage.addActor(musicLabel);
		stage.addActor(effectsLabel);

		stage.addActor(music);
		stage.addActor(effects);

	}

	public static ArrayList<LoadQueue> loadQueue() {
		ArrayList<LoadQueue> list = new ArrayList<LoadQueue>();
		list.add(new LoadQueue("img/HUD/Skins/orange/skin/uiskin.json", Skin.class));
		return list;
	}

}
