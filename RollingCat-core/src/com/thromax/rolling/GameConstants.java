package com.thromax.rolling;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;

public class GameConstants {

	// RELEASE CONSTANTS

	public final static String TITLE = "RollingCat";

	public final static String VERSION = "1.0ALPHA";

	public static boolean isPhone() {
		if (Gdx.app.getType() == ApplicationType.Android || Gdx.app.getType() == ApplicationType.iOS) {
			return true;
		} else {
			return false;
		}
	}

	// CONFIG OPTIONS
	public final static int SCREEN_WIDTH = 1280;
	public final static int SCREEN_HEIGHT = 720;
	// PLAY SCREEN CONSTANTS

	public final static float SPEED = 60 * 50/* (pixels per sec) */;

	public static enum GAMESTATE {
		READY, ROLLING, DEAD, WIN
	};

	// MENU SIZING
	public static final float PADDING = 30;
	public static final float BUTTON_WIDTH = 450.0f;
	public static final float BUTTON_HEIGHT = 95.0f;
	public static final float FONT_SIZE = 2;

}
