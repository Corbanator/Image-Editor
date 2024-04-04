package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D {

	private static int _buttonCount;
	private Color _startColor;

	public boolean Hovered;
	public boolean Clicked;

	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		InputManager.Instance.Buttons.add(this);
		_startColor = recColor;
		_buttonCount += 1;
	}

	public void onClickDown() {
		_recColor = new Color(_startColor.cpy().mul(0.5f));
		generateTexture();
		Clicked = true;
	}

	public void onClickUp() {
		if (Hovered) {
			_recColor = new Color(_startColor.cpy().mul(0.75f));
		} else {
			_recColor = _startColor;
		}
		generateTexture();
		Clicked = false;
	}

	public void onHovered() {
		Hovered = true;
		_recColor = new Color(_startColor.cpy().mul(0.75f));
		generateTexture();
	}

	public void onHoverExit() {
		Hovered = false;
		if (!Clicked) {
			_recColor = _startColor;
			generateTexture();
		}
	}
}
