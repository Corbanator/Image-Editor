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
	public static ImageEditor Instance;
	SpriteBatch batch;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
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
		button1 = new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f + offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f + offset.y),
				buttonScale,
				Color.BLUE);
		button2 = new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f - offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f + offset.y),
				buttonScale,
				Color.RED);
		button3 = new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f + offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f - offset.y),
				buttonScale,
				Color.GREEN);
		button4 = new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f - offset.x,
						ScreenSize.y / 2f - buttonScale.y / 2f - offset.y),
				buttonScale,
				Color.ORANGE);
		button5 = new Button(
				new Vector2(ScreenSize.x / 2f - buttonScale.x / 2f,
						ScreenSize.y / 2f - buttonScale.y / 2f),
				buttonScale,
				Color.WHITE);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		batch.draw(button1.RecTexture, button1.Position.x, button1.Position.y);
		batch.draw(button2.RecTexture, button2.Position.x, button2.Position.y);
		batch.draw(button3.RecTexture, button3.Position.x, button3.Position.y);
		batch.draw(button4.RecTexture, button4.Position.x, button4.Position.y);
		batch.draw(button5.RecTexture, button5.Position.x, button5.Position.y);
		batch.end();
	}
}
