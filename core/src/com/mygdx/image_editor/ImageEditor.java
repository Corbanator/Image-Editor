package com.mygdx.image_editor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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
	EditWindow _editWindow;

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

		new ImageInputOutput();
		Pixmap editMap = ImageInputOutput.Instance.loadImage("blackbuck.bmp");
		CollisionManager collisionManger = new CollisionManager();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);

		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 40);
		_editWindow = new EditWindow(
				new Vector2(ScreenSize.x - editWindowSize.x, 0), editWindowSize, Color.GRAY);
		_editWindow.DoodleTexture = new Texture(editMap);
		Vector2 buttonSize = new Vector2(50, 50);
		Button button = new Button(new Vector2(0, 0), buttonSize, Color.GREEN);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		for (Rec2D Rectangle : Rectangles) {
			batch.draw(Rectangle.RecTexture, Rectangle.Position.x, Rectangle.Position.y, Rectangle.Scale.x,
					Rectangle.Scale.y);
		}
		batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x, _editWindow.Position.y,
				_editWindow.Scale.x,
				_editWindow.Scale.y);
		batch.end();

	}
}
