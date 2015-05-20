package com.github.gawkat.untitledgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.gawkat.untitledgame.Game;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		/*
		 * Application Default Config
		 */
		config.title = "FUKKEN GAME";
		config.samples = 8;
		config.fullscreen = false;
		config.height = 600;
		config.width = 800;

		// TODO

		new LwjglApplication(new Game(), config);
	}
}
