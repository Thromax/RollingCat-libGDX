package com.thromax.rolling.entities.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.thromax.rolling.GameConstants;

public class Player extends Sprite {

	public float speed = GameConstants.SPEED, time = 0f;

	// public boolean dead = false;
	public GameConstants.GAMESTATE currentState = GameConstants.GAMESTATE.READY;

	public TiledMapTileLayer collisionLayer;
	private float pWidth, pHeight;

	// Animation Stuff
	private TextureAtlas catAtlas = new TextureAtlas(Gdx.files.internal("img/CatRoll/RollingCat.pack"));
	private Animation<?> rollingCatAnim = initializeAnimation(catAtlas, 1 / 15f);

	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer = collisionLayer;
		pWidth = collisionLayer.getTileWidth();
		pHeight = collisionLayer.getTileHeight();

	}

	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());

		if (!rollingCatAnim.isAnimationFinished(time)) {
			time += Gdx.graphics.getDeltaTime();

		} else {
			time = 0;
		}

		if (currentState == GameConstants.GAMESTATE.ROLLING) {
			setRegion((TextureRegion) rollingCatAnim.getKeyFrame(time));
		} else {
			setRegion((TextureRegion) rollingCatAnim.getKeyFrame(0));
		}
		super.draw(batch);
	}

	private void update(float delta) {
		setSize(pWidth, pHeight);

		if (currentState == GameConstants.GAMESTATE.ROLLING) {
			// Save previous position
			float oldX = getX(), oldY = getY();

			// Move on X
			setX(getX() + speed * delta);

			// Touching obstacle
			if (collidesLeft("blocked")) {
				currentState = GameConstants.GAMESTATE.DEAD;
				speed = 0;
				setX(oldX);
				setY(oldY);

			}

			// Touching end
			if (collidesLeft("win") && currentState == GameConstants.GAMESTATE.ROLLING) {
				currentState = GameConstants.GAMESTATE.WIN;
				speed = 0;
				setX(oldX);
				setY(oldY);
			}
		}

	}

	private boolean hasCellProperty(float x, float y, String s, boolean tile) {
		Cell cell;
		if (!tile) {
			cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()),
					(int) (y / collisionLayer.getTileHeight()));
		} else {
			cell = collisionLayer.getCell((int) (x), (int) (y));
		}

		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(s);
	}

	public boolean collidesLeft(String s) {

		if (hasCellProperty(getX() + getWidth(), getY(), s, false)) {
			return true;
		} else {
			return false;
		}
	}

	public void dispose() {
		catAtlas.dispose();
	}

	private Animation<?> initializeAnimation(TextureAtlas t, float frameDuration) {
		return new Animation<Object>(frameDuration, t.getRegions());
	}

	public ArrayList<Integer> search4CellProperty(String property) {
		for (float x = 0; x < collisionLayer.getWidth(); x++) {
			for (float y = 0; y < collisionLayer.getHeight(); y++) {
				boolean prop = hasCellProperty(x, y, property, true);
				if (prop) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(0, (int) x);
					list.add(1, (int) y);
					return list;
				}

			}

		}
		return null;

	}
}
