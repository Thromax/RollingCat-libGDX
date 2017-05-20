package com.thromax.rolling;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.thromax.rolling.screens.LoadingScreen;
import com.thromax.rolling.screens.StartMenu;

public class Main extends Game {

	public AssetManager assets;

	public LoadingScreen loadingScreen;

	@Override
	public void create() {
		assets = new AssetManager();

		loadingScreen = new LoadingScreen(this, new StartMenu(this, this), StartMenu.loadQueue());

		setScreen(loadingScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();

		this.getScreen().dispose();

	}

	@Override
	public void render() {
		super.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !GameConstants.PHONE) {
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	@Override
	public void pause() {
		super.pause();

	}

	@Override
	public void resume() {
		super.resume();

	}

	public Main getMain() {

		return this;
	}

}
