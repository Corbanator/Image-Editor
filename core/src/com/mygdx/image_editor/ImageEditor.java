package com.mygdx.image_editor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	Rec2D rectangle;
	static int Height = 480;
	static int Width = 584;

	public ImageEditor(int width, int height) {
		Width = width;
		Height = height;
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		rectangle = new Rec2D(
				new Vector2(Width / 2 - 100, Height / 2 - 100),
				new Vector2(200, 200), Color.RED);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		batch.draw(rectangle.RecTexture, rectangle.Position.x, rectangle.Position.y);
		batch.end();
	}
}
