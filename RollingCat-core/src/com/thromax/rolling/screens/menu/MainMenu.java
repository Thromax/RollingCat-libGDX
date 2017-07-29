package com.thromax.rolling.screens.menu;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.Main;
import com.thromax.rolling.entities.misc.LoadQueue;
import com.thromax.rolling.entities.misc.LuckyCat;
import com.thromax.rolling.misc.MapLoader;
import com.thromax.rolling.screens.LoadingScreen;

public class MainMenu implements Screen {

	// Elements:
	private TextButton play;
	private TextButton setting;
	private TextButton credit;
	private LuckyCat luckyCat;

	// Skin and Stage:
	private Skin skin;
	private Stage stage;

	// Text of the Buttons:
	private final String string_Play = "Play";
	private final String string_Settings = "Settings";
	private final String string_Credits = "Credits";

	// Input handler of the Buttons:
	private Main main;
	private Game game;

	// First Separate up:
	private final float firstSeparate = GameConstants.BUTTON_HEIGHT + 10;

	// Counter:
	private byte counter = 0;

	public MainMenu(Game game, Main main) {
		this.game = game;
		this.main = main;
	}

	@Override
	public void show() {
		// Create cat
		luckyCat = new LuckyCat(new Sprite());
		luckyCat.setScale(4);
		luckyCat.setPosition(0, 150);

		skin = new Skin(Gdx.files.internal("img/HUD/Skins/orange/skin/uiskin.json"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		initButtons();
	}

	private void initButtons() {

		play = new TextButton(string_Play, skin);
		setting = new TextButton(string_Settings, skin);
		credit = new TextButton(string_Credits, skin);

		counter++;
		play.setPosition(GameConstants.SCREEN_WIDTH / 2 - GameConstants.BUTTON_WIDTH / 2,
				GameConstants.SCREEN_HEIGHT / 2 - firstSeparate * counter);
		play.setSize(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		play.getLabel().setFontScale(GameConstants.FONT_SIZE);
		play.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				MapLoader ml = new MapLoader();
				ml.load(game, main, "level1", false);
				// game.setScreen(new LoadingScreen(main, new LevelScreen(main,
				// game), LevelScreen.loadQueue()));

			}
		});
		counter++;
		setting.setPosition(GameConstants.SCREEN_WIDTH / 2 - GameConstants.BUTTON_WIDTH / 2,
				GameConstants.SCREEN_HEIGHT / 2 - firstSeparate * counter);
		setting.setSize(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		setting.getLabel().setFontScale(GameConstants.FONT_SIZE);
		setting.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				game.setScreen(new LoadingScreen(main, new SettingScreen(main, game), SettingScreen.loadQueue()));
			}
		});
		counter++;
		credit.setPosition(GameConstants.SCREEN_WIDTH / 2 - GameConstants.BUTTON_WIDTH / 2,
				GameConstants.SCREEN_HEIGHT / 2 - firstSeparate * counter);
		credit.setSize(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		credit.getLabel().setFontScale(GameConstants.FONT_SIZE);
		credit.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {

			}
		});
		counter++;

		stage.addActor(play);
		stage.addActor(setting);
		stage.addActor(credit);

	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(.56f, .91f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

		stage.getBatch().begin();
		luckyCat.draw(stage.getBatch());
		stage.getBatch().end();
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void dispose() {
		skin.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void resume() {

	}

	public static ArrayList<LoadQueue> loadQueue() {
		ArrayList<LoadQueue> list = new ArrayList<LoadQueue>();
		list.add(new LoadQueue("img/HUD/Skins/orange/skin/uiskin.json", Skin.class));
		return list;
	}

}
