package com.thromax.rolling.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.entities.player.Player;

public class Play implements Screen {

	// Map creation
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private ArrayList<Integer> startPropList;
	private float way1, way2, way3, way4;

	// Camera stuff
	private OrthographicCamera camera;
	private Viewport view;

	private Player player;

	@Override
	public void show() {

		// Shows and initializes map
		map = new TmxMapLoader().load("maps/level 1/level1.tmx");
		// Shows and initializes map renderer
		renderer = new OrthogonalTiledMapRenderer(map);

		// Creates the player (cat)
		player = new Player(new Sprite(new Texture("img/CatRoll/RollingCat.png")),
				(TiledMapTileLayer) map.getLayers().get("Blocks"));

		// Initializes camera
		camera = new OrthographicCamera();
		view = new FillViewport(Gdx.graphics.getWidth() * 5.7f, Gdx.graphics.getHeight() * 5.7f, camera);
		view.apply();

		// Sets the ways' y coordinates
		way1 = 11 * player.collisionLayer.getTileHeight();
		way2 = 8 * player.collisionLayer.getTileHeight();
		way3 = 5 * player.collisionLayer.getTileHeight();
		way4 = 2 * player.collisionLayer.getTileHeight();

		startPropList = player.search4CellProperty("start");

		setPlayerStart();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.56f, .91f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getX() + camera.viewportWidth / 2 - player.collisionLayer.getTileWidth() * 2, way2,
				0);
		camera.update();

		// Makes Map renderer Render (Professor Obvious was here)
		renderer.getBatch().enableBlending();
		renderer.render();
		renderer.setView(camera);

		// Input Handler
		inputPressed();

		renderer.getBatch().begin();
		// Rendering player
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
	}

	// Input Handler
	private void inputPressed() {

		if (((Gdx.input.isKeyJustPressed(Keys.UP) && !GameConstants.PHONE)
				|| ((Gdx.input.justTouched() && (Gdx.input.getX() < (Gdx.graphics.getWidth() / 2)))
						&& GameConstants.PHONE))
				&& player.currentState == GameConstants.GAMESTATE.ROLLING) {

			moveToWay(true);

		}

		else if (((Gdx.input.isKeyJustPressed(Keys.DOWN) && !GameConstants.PHONE)
				|| ((Gdx.input.justTouched() && (Gdx.input.getX() > (Gdx.graphics.getWidth() / 2)))
						&& GameConstants.PHONE))
				&& player.currentState == GameConstants.GAMESTATE.ROLLING) {

			moveToWay(false);

		}
		if ((Gdx.input.isKeyJustPressed(Keys.Z) && !GameConstants.PHONE)
				|| ((Gdx.input.justTouched() && player.currentState != GameConstants.GAMESTATE.ROLLING))
						&& GameConstants.PHONE) {

			switch (player.currentState) {

			case READY:
				player.currentState = GameConstants.GAMESTATE.ROLLING;
				break;

			case ROLLING:
			case WIN:
			case DEAD:
				restart();
				break;
			default:
				break;

			}
		}

		if ((Gdx.input.isKeyJustPressed(Keys.ESCAPE) && !GameConstants.PHONE)) {
			Gdx.app.exit();
			System.exit(0);
		}

	}

	// Predefined player movement possibilities
	public void moveToWay(boolean dir) {
		if (dir) {
			if (player.getY() == way2) {
				player.setY(way1);
			} else if (player.getY() == way3) {
				player.setY(way2);

			} else if (player.getY() == way4) {
				player.setY(way3);
			}
		} else {
			if (player.getY() == way1) {
				player.setY(way2);

			} else if (player.getY() == way2) {
				player.setY(way3);

			} else if (player.getY() == way3) {
				player.setY(way4);
			}
		}

	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		// Unload shit
		map.dispose();
		renderer.dispose();
		player.dispose();
	}

	// I'm not gonna explain what this void does, too obvious.
	public void restart() {

		player.currentState = GameConstants.GAMESTATE.READY;
		player.speed = GameConstants.SPEED;
		try {
			player.setPosition(startPropList.get(0) * player.collisionLayer.getTileWidth(), way2);
		} catch (NullPointerException e) {
			player.setPosition(3 * player.collisionLayer.getTileWidth(), way2);
		}

	}

	/*
	 * Sets the initial player position by searching for blocks with the "start"
	 * property
	 */
	private void setPlayerStart() {
		try {

			player.setPosition(startPropList.get(0) * player.collisionLayer.getTileWidth(), way2);
		} catch (NullPointerException e) {
			player.setPosition(3 * player.collisionLayer.getTileWidth(), way2);
		}

	}

}
