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
import com.thromax.rolling.GameConstants;
import com.thromax.rolling.entities.player.Player;

public class Play implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;

	private ArrayList<Integer> startPropList;

	private float way1, way2, way3;

	@Override
	public void show() {
		// Shows and initializes map
		map = new TmxMapLoader().load("maps/level 1/level1.tmx");
		// Shows and initializes map renderer
		renderer = new OrthogonalTiledMapRenderer(map);
		// Initializes camera
		camera = new OrthographicCamera();

		// Creates the player (cat)
		player = new Player(new Sprite(new Texture("img/CatRoll/RollingCat.png")),
				(TiledMapTileLayer) map.getLayers().get("Blocks"));

		// Sets the way y coordinates
		way1 = 10 * player.collisionLayer.getTileHeight();
		way2 = 7 * player.collisionLayer.getTileHeight();
		way3 = 4 * player.collisionLayer.getTileHeight();

		startPropList = player.search4CellProperty("start");
		try {
			player.setPosition(startPropList.get(0) * player.collisionLayer.getTileWidth(), way2);
		} catch (NullPointerException e) {
			player.setPosition(3 * player.collisionLayer.getTileWidth(), way2);
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .8f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getX() + camera.viewportWidth / 2 - player.collisionLayer.getTileWidth() * 2, way2,
				0);
		camera.update();

		// Makes Map renderer Render (Professor Obvious was here)
		renderer.render();
		renderer.setView(camera);

		inputPressed();

		renderer.getBatch().begin();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
	}

	private void inputPressed() {

		if (((Gdx.input.isKeyJustPressed(Keys.UP) && !GameConstants.PHONE) || ((Gdx.input.justTouched()&&(Gdx.input.getX()<(Gdx.graphics.getWidth()/2))) && GameConstants.PHONE)) && player.currentState == GameConstants.GAMESTATE.ROLLING) {
			/*
			 * if ((Gdx.input.isKeyJustPressed(Keys.UP) && ((player.currentState
			 * == GameConstants.GAMESTATE.ROLLING) && !GameConstants.PHONE))) {
			 */
			moveToWay(true);

		}

		else if (((Gdx.input.isKeyJustPressed(Keys.DOWN) && !GameConstants.PHONE) || ((Gdx.input.justTouched()&&(Gdx.input.getX()>(Gdx.graphics.getWidth()/2))) && GameConstants.PHONE)) && player.currentState == GameConstants.GAMESTATE.ROLLING) {
			
			moveToWay(false);

		}
		if ((Gdx.input.isKeyJustPressed(Keys.Z) && !GameConstants.PHONE)) {

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

	public void moveToWay(boolean dir) {
		if (dir) {
			if (player.getY() == way2) {
				player.setY(way1);
			} else if (player.getY() == way3) {
				player.setY(way2);

			}
		} else {
			if (player.getY() == way1) {
				player.setY(way2);

			} else if (player.getY() == way2) {
				player.setY(way3);

			}
		}

	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width * 7;
		camera.viewportHeight = height * 7;
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

	public void restart() {

		player.currentState = GameConstants.GAMESTATE.READY;
		player.speed = GameConstants.SPEED;
		try {
			player.setPosition(startPropList.get(0) * player.collisionLayer.getTileWidth(), way2);
		} catch (NullPointerException e) {
			player.setPosition(3 * player.collisionLayer.getTileWidth(), way2);
		}

	}

}
