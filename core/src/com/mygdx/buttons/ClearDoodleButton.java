package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.image_editor.EditWindow;

public class ClearDoodleButton extends Button {
	public ClearDoodleButton(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		ButtonText = "Clear";
	}

	public void onClickUp(Vector2 clickPosition) {
		super.onClickUp(clickPosition);
		if (Hovered) {
			EditWindow edit = EditWindow.Instance;
			edit.DoodleMap = new Pixmap((int) edit.Scale.x, (int) edit.Scale.y, Format.RGBA8888);
			edit.DoodleMap.setColor(edit.DrawColor);
			edit.DoodleTexture = new Texture(edit.DoodleMap);
		}
	}
}
