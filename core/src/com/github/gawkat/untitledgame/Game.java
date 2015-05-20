package com.github.gawkat.untitledgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class Game extends ApplicationAdapter {

	public PerspectiveCamera camera;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;
	public Environment environment;
	public CameraInputController camController;

	// TODO CONFIG
	public float fov = 85;

	@Override
	public void create() {

		// Music test
		Sound sound = Gdx.audio.newSound(Gdx.files
				.internal("audio/music/Genesis.mp3"));
		sound.play(0.1f);

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
		camController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(camController);
	}

	@Override
	public void render() {
		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(camera);
		modelBatch.render(instance, environment);
		modelBatch.end();
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
}
