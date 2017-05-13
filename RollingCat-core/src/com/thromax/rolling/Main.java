package com.thromax.rolling;

import com.badlogic.gdx.Game;
import com.thromax.rolling.screens.Play;
import com.thromax.rolling.screens.StartMenu;

public class Main extends Game {

	@Override
	public void create() {
		setScreen(new StartMenu(this));
	}

	@Override
	public void dispose() {
		super.dispose();

	}

	@Override
	public void render() {
		super.render();

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


}