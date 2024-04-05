package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable {
	public Texture DoodleTexture;
	private Pixmap _doodleMap;

	public boolean Clicked;

	public EditWindow(Vector2 position, Vector2 scale, Color backgroundColor) {
		super(position, scale, backgroundColor);
		InputManager.Instance.Clickables.add(this);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);
	}

	public void onClickUp(Vector2 mousePosition) {
		Clicked = false;
	}

	private void paintAtPosition(Vector2 worldPosition) {
		Vector2 pixmapPosition = convertToPixmapCoordinates(worldPosition);
		_doodleMap.drawPixel((int) (pixmapPosition.x), (int) (pixmapPosition.y));
		DoodleTexture = new Texture(_doodleMap);
	}

	public void onClickDragged(Vector2 position) {
		paintAtPosition(position);
	}

	public void onClickDown(Vector2 position) {
		Clicked = true;
		paintAtPosition(position);
	}

	private Vector2 convertToPixmapCoordinates(Vector2 worldCoordinates) {
		return new Vector2(
				worldCoordinates.x - Position.x,
				ImageEditor.Instance.ScreenSize.y - worldCoordinates.y
						- (ImageEditor.Instance.ScreenSize.y - (Position.y + Scale.y)));
	}

	public boolean isClicked() {
		return Clicked;
	}
}
