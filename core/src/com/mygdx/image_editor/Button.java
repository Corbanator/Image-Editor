package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D implements IClickable, IHoverable {

	private Color _startColor;

	public boolean Hovered;
	public boolean Clicked;

	public Button(Vector2 position, Vector2 scale, Color recColor) {
		super(position, scale, recColor);
		InputManager.Instance.Clickables.add(this);
		InputManager.Instance.Hoverables.add(this);
		_startColor = recColor;
	}

	public void onClickDown(Vector2 position) {
		_recColor = new Color(_startColor.cpy().mul(0.5f));
		generateTexture();
		Clicked = true;
	}

	public void onClickUp(Vector2 position) {
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

	@Override
	public void onClickDragged(Vector2 mousePosition) {
	}

	public boolean isHovered() {
		return Hovered;
	}

	public boolean isClicked() {
		return Clicked;
	}

}
