package com.thromax.rolling;

public class GameConstants {

	// RELEASE CONSTANTS

	public final static String TITLE = "RollingCat";

	public final static String VERSION = "1.0ALPHA";

	public final static boolean PHONE = false;

	// CONFIG OPTIONS
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

	// PLAY SCREEN CONSTANTS

	public final static float SPEED = 60 * 50/* (pixels per sec) */;

	public static enum GAMESTATE {
		READY, ROLLING, DEAD, WIN
	};

}
