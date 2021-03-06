package com.thromax.rolling.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.Main;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GameConstants.TITLE + " " + GameConstants.VERSION;
		config.vSyncEnabled = true;
		//config.useGL30 = true;
		config.width = GameConstants.SCREEN_WIDTH;
		config.height = GameConstants.SCREEN_HEIGHT;
		new LwjglApplication(new Main(), config);
	}
}
