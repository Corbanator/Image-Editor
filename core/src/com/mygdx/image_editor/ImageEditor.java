package com.mygdx.image_editor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	SpriteBatch batch;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	public Vector2 ScreenSize;

	public ImageEditor(int width, int height) {
		ScreenSize = new Vector2(width, height);
		Instance = this;
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();

		CollisionManager collisionManger = new CollisionManager();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);

		Vector2 buttonScale = new Vector2(100, 100);
		Vector2 offset = new Vector2(150, 150);
		new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f + offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f + offset.y),
				buttonScale,
				Color.BLUE);
		new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f - offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f + offset.y),
				buttonScale,
				Color.RED);
		new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f + offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f - offset.y),
				buttonScale,
				Color.GREEN);
		new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f - offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f - offset.y),
				buttonScale,
				Color.ORANGE);
		new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f,
						ScreenSize.y / 2f - buttonScale.y / 2f),
				buttonScale,
				Color.WHITE);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		for (Rec2D Rectangle : Rectangles) {
			batch.draw(Rectangle.RecTexture, Rectangle.Position.x, Rectangle.Position.y, Rectangle.Scale.x,
					Rectangle.Scale.y);
		}
		batch.end();
	}
}
