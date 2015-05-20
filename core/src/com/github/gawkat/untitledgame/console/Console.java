package com.github.gawkat.untitledgame.console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * @author Gawkat
 *
 */
public class Console {

	public BitmapFont consoleFont;
	public SpriteBatch spriteBatch;

	public ConsoleTextHandler consoleTextHandler = new ConsoleTextHandler();

	public static boolean console_open = false;

	public boolean toggleConsole() {
		console_open = !console_open;
		if (console_open) {
			Gdx.input.setInputProcessor(consoleTextHandler);
		}
		return console_open;
	}

	public void render() {
		if (console_open) {
			spriteBatch.begin();
			consoleFont.draw(spriteBatch, "Test", 2, 16);
			spriteBatch.end();
		}
	}

	public void create() {
		spriteBatch = new SpriteBatch();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/Visitor.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		consoleFont = generator.generateFont(parameter);
		generator.dispose();
	}

}
