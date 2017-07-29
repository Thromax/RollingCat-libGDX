package com.thromax.rolling.misc;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.SerializationException;
import com.thromax.rolling.Main;
import com.thromax.rolling.entities.misc.LoadQueue;
import com.thromax.rolling.screens.LoadingScreen;
import com.thromax.rolling.screens.Play;

public class MapLoader {

	// Scene changing
	@SuppressWarnings("unused")
	private Game game;
	@SuppressWarnings("unused")
	private Main main;

	// Getting map
	private String finalPath;
	private TiledMap map;

	public void load(Game game, Main main, String name, boolean out) {
		// Scene changing (Initialization)
		this.game = game;
		this.main = main;

		// Map selection
		try {
			if (!out) {
				finalPath = "maps/" + name + "/" + name + ".tmx";
				checkMapLoc(finalPath);
				game.setScreen(new LoadingScreen(main, new Play(map), this.loadQueue()));

			} else {
				// MOD SUPPORT WILL BE ADDED IN THE FUTURE!!

			}
		} catch (SerializationException e) {
			System.out.println("The selected map could not be found! *cries*");
		}

	}

	private void checkMapLoc(String finalPath) {
		map = new TmxMapLoader().load(finalPath);
	}

	// Returns the requirements needed to load this class
	public ArrayList<LoadQueue> loadQueue() {
		ArrayList<LoadQueue> list = new ArrayList<LoadQueue>();
		for (LoadQueue ps : Play.loadQueue()) {
			list.add(ps);

		}
		list.add(new LoadQueue(finalPath, TiledMap.class));
		return list;
	}

}
