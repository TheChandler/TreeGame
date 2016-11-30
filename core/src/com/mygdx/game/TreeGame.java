package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class TreeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameStateManager gsm;
	OrthographicCamera cam;
	public static int gameWidth=1080;
	public static int gameHeight=1920;


	@Override
	public void create () {
		gsm=new GameStateManager();
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 140, 0, 1);
		gsm.push(new Forest(gsm));

		cam = new OrthographicCamera(gameWidth,gameHeight);

		cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
		cam.update();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);

		batch.begin();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
