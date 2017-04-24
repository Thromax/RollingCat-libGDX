package com.thromax.rolling.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.Main;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);
		config.title = GameConstants.TITLE + " " + GameConstants.VERSION;
		config.vSyncEnabled = true;
		config.useGL30 = true;
		/* w640 h480 */
		config.width = GameConstants.WIDTH;
		config.height = GameConstants.HEIGHT;

	}
}
