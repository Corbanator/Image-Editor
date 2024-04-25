package com.mygdx.image_editor;

import com.mygdx.buttons.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.Button;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	SpriteBatch batch;
	EditWindow _editWindow;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();

	public Vector2 ScreenSize;
	private float _editWindowWidthPercentage = 0.8f;
	private Vector2 newButtonPosition;
	private Vector2 buttonSize;
	private boolean buttonIsSecond;

	private BitmapFont _font;

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
		initializeUtilityClasses();
		createGraphicalElements();
	}

	private void initializeUtilityClasses() {
		new CollisionManager();
		new ImageInputOutput();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		_font = new BitmapFont();
	}

	private void createGraphicalElements() {
		batch = new SpriteBatch();

		Vector2 editWindowSize = new Vector2(ScreenSize.x * _editWindowWidthPercentage, ScreenSize.y - 50);
		_editWindow = new EditWindow(new Vector2(ScreenSize.x - editWindowSize.x, 0), editWindowSize);

		float buttonDimension = ((1 - _editWindowWidthPercentage) / 2) * ScreenSize.x;
		buttonSize = new Vector2(buttonDimension, buttonDimension);
		newButtonPosition = Vector2.Zero;
		buttonIsSecond = false;
		initializeButtons();
		new SaveButton(new Vector2(0, ScreenSize.y - 25), new Vector2(75, 25), Color.SLATE);
		new ExitButton(new Vector2(85, ScreenSize.y - 25), new Vector2(75, 25), Color.SLATE);
		new ClearDoodleButton(new Vector2(170, ScreenSize.y - 25), new Vector2(75, 25), Color.SLATE);
	}

	private void initializeButtons() {
		Array<Color> colors = new Array<Color>();
		colors.add(Color.RED);
		colors.add(Color.ORANGE);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.PURPLE);
		colors.add(Color.BLACK);
		colors.add(Color.WHITE);

		for (Color color : colors) {
			makeNewButton(color);
		}
	}

	private void makeNewButton(Color color) {
		new ColorButton(newButtonPosition, buttonSize, color);
		if (buttonIsSecond) {
			newButtonPosition = new Vector2(newButtonPosition.x - buttonSize.x,
					newButtonPosition.y + buttonSize.y);
		} else {
			newButtonPosition = new Vector2(newButtonPosition.x + buttonSize.x, newButtonPosition.y);
		}
		buttonIsSecond = !buttonIsSecond;
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		for (Rec2D Rectangle : Rectangles) {
			batch.draw(Rectangle.RecTexture, Rectangle.Position.x, Rectangle.Position.y, Rectangle.Scale.x,
					Rectangle.Scale.y);
		}
		for (Rec2D Rectangle : Rectangles) {
			batch.draw(Rectangle.Outline.OutlineTex, Rectangle.Position.x, Rectangle.Position.y,
					Rectangle.Scale.x, Rectangle.Scale.y);
		}
		batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x, _editWindow.Position.y,
				_editWindow.Scale.x,
				_editWindow.Scale.y);
		for (Rec2D Rectangle : Rectangles) {
			if (Rectangle instanceof Button) {
				Button button = (Button) Rectangle;
				if (button.ButtonText == null)
					continue;
				_font.draw(batch, button.ButtonText, button.Position.x,
						button.Position.y + button.Scale.y * 0.75f,
						button.Scale.x, Align.center, false);
			}
		}
		batch.end();

	}

	public void filesImported(String[] filePaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if (map == null)
			return;
		_editWindow.RecTexture = new Texture(map);
	}
}
