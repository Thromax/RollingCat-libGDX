package com.thromax.rolling.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.thromax.rolling.GameConstants;

public class ButtonBack extends TextButton {

	// Button Width
	private final static float size = 100;
	private final static String text = "Back";

	public ButtonBack() {

		super(text, new Skin(Gdx.files.internal("img/HUD/Skins/orange/skin/uiskin.json")));
		this.setSize(size, size);
		this.setPosition(GameConstants.PADDING, GameConstants.SCREEN_HEIGHT - size - GameConstants.PADDING);
		this.getLabel().setFontScale(GameConstants.FONT_SIZE);

	}
}
