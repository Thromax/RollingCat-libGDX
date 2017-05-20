package com.thromax.rolling.entities.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.thromax.rolling.GameConstants;

public class LuckyCat extends Sprite {
	// Animation Stuff
	private TextureAtlas catAtlas = new TextureAtlas(Gdx.files.internal("img/Animations/CatLucky/CatLucky.atlas"));
	private Animation<?> luckyCatAnim = initializeAnimation(catAtlas, 1 / 15f);

	private float time = 0;

	public LuckyCat(Sprite sprite) {
		super(sprite);
		this.setSize(93, 80);
	}

	@Override
	public void draw(Batch batch) {
		// Animation Stuff
		if (!luckyCatAnim.isAnimationFinished(time)) {
			time += Gdx.graphics.getDeltaTime();

		} else {
			time = 0;
		}

		setRegion((TextureRegion) luckyCatAnim.getKeyFrame(time));

		super.draw(batch);
	}

	public void dispose() {

	}

	private Animation<?> initializeAnimation(TextureAtlas t, float frameDuration) {
		return new Animation<Object>(frameDuration, t.getRegions());
	}
}
