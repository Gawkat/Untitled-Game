package com.github.gawkat.untitledgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.github.gawkat.untitledgame.console.Console;

/**
 * @author Gawkat
 *
 */
public class Game extends ApplicationAdapter {

	public PerspectiveCamera camera;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;
	public Environment environment;
	public static MovementHandler movementHandler;

	// TODO CONFIG
	public float fov = 85;
	public boolean enable_console = true;
	public Console console;
	public boolean draw_fps = true;

	@Override
	public void create() {
		// TODO
		movementHandler = new MovementHandler();
		Gdx.input.setInputProcessor(movementHandler);
		// TODO
		if (enable_console) {
			console = new Console();
			console.create();
		}

		// Music test TODO
		Sound sound = Gdx.audio.newSound(Gdx.files
				.internal("audio/music/Genesis.mp3"));
		// sound.play(0.1f);

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f,
				0.4f, 0.4f, 1f));
		environment.add(new PointLight().set(Color.OLIVE, -2.5f, 10.0f, -0.2f,
				65));

		modelBatch = new ModelBatch();

		camera = new PerspectiveCamera(fov, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camera.position.set(10f, 10f, 10f);
		camera.lookAt(0, 0, 0);
		camera.near = 1f;
		camera.far = 300f;
		camera.update();

		ModelLoader<?> modelLoader = new ObjLoader();
		model = modelLoader.loadModel(Gdx.files.internal("obj/de_dust2_b.obj"));
		instance = new ModelInstance(model);
	}

	@Override
	public void render() {
		camera.update();
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(camera);
		modelBatch.render(instance, environment);
		modelBatch.end();

		// TODO
		if (enable_console) {
			console.render();
		}
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		model.dispose();
	}

	@Override
	public void resume() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	public class MovementHandler implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			// TODO WTF?!
			if (keycode == Keys.W) {
				camera.position.x++;
			}
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			if (character == '§' && enable_console) {
				console.toggleConsole();
			}
			return false;
		}

		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchUp(int x, int y, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchDragged(int x, int y, int pointer) {
			return false;
		}

		@Override
		public boolean mouseMoved(int x, int y) {
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			return false;
		}
	}

}
