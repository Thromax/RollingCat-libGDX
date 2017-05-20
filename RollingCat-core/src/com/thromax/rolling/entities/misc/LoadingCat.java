package com.thromax.rolling.entities.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoadingCat extends Sprite {
	// Animation Stuff
	private TextureAtlas catAtlas = new TextureAtlas(Gdx.files.internal("img/Animations/CatLoading/CatLoading.atlas"));
	private Animation<?> loadCatAnim = initializeAnimation(catAtlas, 1 / 10f);

	private float time = 0;

	public LoadingCat(Sprite sprite) {
		super(sprite);
		this.setSize(20, 11);
	}

	@Override
	public void draw(Batch batch) {
		// Animation Stuff
		if (!loadCatAnim.isAnimationFinished(time)) {
			time += Gdx.graphics.getDeltaTime();

		} else {
			time = 0;
		}

		setRegion((TextureRegion) loadCatAnim.getKeyFrame(time));

		super.draw(batch);
	}

	public void dispose() {

	}

	private Animation<?> initializeAnimation(TextureAtlas t, float frameDuration) {
		return new Animation<Object>(frameDuration, t.getRegions());
	}

}
