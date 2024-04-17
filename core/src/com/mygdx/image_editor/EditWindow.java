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
	private Vector2 _previousPaintPosition;
	private int drawradius;

	public EditWindow(Vector2 position, Vector2 scale, Color backgroundColor) {
		super(position, scale, backgroundColor);
		InputManager.Instance.Clickables.add(this);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);
		drawradius = 1;
	}

	public void onClickUp(Vector2 mousePosition) {
		Clicked = false;
		_previousPaintPosition = null;
	}

	private void paintAtPosition(Vector2 oldWorldPosition, Vector2 worldPosition) {
		Vector2 pixmapPosition = convertToPixmapCoordinates(worldPosition);
		Vector2 oldPixmapPosition = convertToPixmapCoordinates(oldWorldPosition);
		for (int i = -(drawradius); i <= drawradius; i++) {
			for (int j = -(drawradius); j <= drawradius; j++) {
				_doodleMap.drawLine((int) (oldPixmapPosition.x + i), (int) (oldPixmapPosition.y + j),
						(int) (pixmapPosition.x + i),
						(int) (pixmapPosition.y + j));
			}
		}
		_doodleMap.drawLine((int) (oldPixmapPosition.x), (int) (oldPixmapPosition.y),
				(int) (pixmapPosition.x),
				(int) (pixmapPosition.y));
		DoodleTexture = new Texture(_doodleMap);
	}

	public void onClickDragged(Vector2 position) {
		paintAtPosition(_previousPaintPosition, position);
		_previousPaintPosition = position;
	}

	public void onClickDown(Vector2 position) {
		Clicked = true;
		_previousPaintPosition = position;
		paintAtPosition(_previousPaintPosition, position);
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
