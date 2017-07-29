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
import com.thromax.rolling.entities.misc.LoadQueue;

public class Player extends Sprite {

	public float speed = GameConstants.SPEED, time = 0f;

	public GameConstants.GAMESTATE currentState = GameConstants.GAMESTATE.READY;
	private float pWidth, pHeight;
	private float oldX, oldY;

	public TiledMapTileLayer collisionLayer;

	// Animation Stuff
	private TextureAtlas catAtlas = new TextureAtlas(Gdx.files.internal("img/Animations/CatRoll/RollingCat.pack"));
	private Animation<?> rollingCatAnim = initializeAnimation(catAtlas, 1 / 15f);

	public Player(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
		// Sets player to tiles' size
		pWidth = collisionLayer.getTileWidth();
		pHeight = collisionLayer.getTileHeight();

	}

	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());

		// Animation Stuff
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
			oldX = getX();
			oldY = getY();

			// Move on X
			setX(getX() + speed * delta);

			// Touching obstacle
			if (checkCollision("blocked")) {
				currentState = GameConstants.GAMESTATE.DEAD;

			}

			// Touching end of the map
			if ((getX() + getWidth()) >= (collisionLayer.getTileWidth() * collisionLayer.getWidth())) {
				currentState = GameConstants.GAMESTATE.DEAD;
			}

			// Touching goal
			if (checkCollision("win") && currentState == GameConstants.GAMESTATE.ROLLING) {
				currentState = GameConstants.GAMESTATE.WIN;

			}
		}

		if (currentState == GameConstants.GAMESTATE.DEAD || currentState == GameConstants.GAMESTATE.WIN) {
			speed = 0;
			setX(oldX);
			setY(oldY);

		}

	}

	// Checks if x,y cell contains s property, also you can give coordinates by
	// tiles or normal coordinates switching tile boolean
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

	// I know this is such a stupid thing, but it is easier for future changes
	public boolean checkCollision(String s) {
		return collidesLeft(s);
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

	// Returns the requirements needed to load this class
	public static ArrayList<LoadQueue> loadQueue() {
		ArrayList<LoadQueue> list = new ArrayList<LoadQueue>();

		list.add(new LoadQueue("img/Animations/CatRoll/RollingCat.pack", TextureAtlas.class));

		return list;
	}
}
