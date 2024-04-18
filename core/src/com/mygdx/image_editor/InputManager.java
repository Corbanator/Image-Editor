package com.mygdx.image_editor;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
	public static InputManager Instance;

	private boolean _controlPressed;

	public Array<IClickable> Clickables = new Array<IClickable>();
	public Array<IHoverable> Hoverables = new Array<IHoverable>();

	public InputManager() {
		Instance = this;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (_controlPressed && keycode == Keys.S)
			if (_controlPressed && keycode == Keys.S)
				try {
					ImageInputOutput.Instance.saveImage("test.bmp");
					System.out.println("saved.");
				} catch (IOException e) {
					e.printStackTrace();
				}
		if (keycode == Keys.CONTROL_LEFT)
			_controlPressed = true;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.CONTROL_LEFT)
			_controlPressed = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Array<IClickable> collisions = CollisionManager.Instance
				.getClicked(convertToWorldCoordinates(
						new Vector2(screenX, screenY)));
		for (IClickable collision : collisions) {
			collision.onClickDown(convertToWorldCoordinates(new Vector2(screenX, screenY)));
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for (IClickable clickable : Clickables) {
			if (clickable.isClicked()) {
				clickable.onClickUp(convertToWorldCoordinates(new Vector2(screenX, screenY)));
			}
		}
		return true;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for (IClickable clickable : Clickables) {
			if (clickable.isClicked()) {
				clickable.onClickDragged(convertToWorldCoordinates(new Vector2(screenX, screenY)));
			}
		}
		return mouseMoved(screenX, screenY);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Array<IHoverable> collisions = CollisionManager.Instance
				.getHovered(convertToWorldCoordinates(
						new Vector2(screenX, screenY)));
		for (IHoverable collision : collisions) {
			if (!collision.isHovered()) {
				collision.onHovered();
			}
		}
		for (IHoverable hoverable : Hoverables) {
			if (!collisions.contains(hoverable, true)) {
				if (hoverable.isHovered()) {
					hoverable.onHoverExit();
				}
			}
		}
		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

	private Vector2 convertToWorldCoordinates(Vector2 screenCoordinates) {
		return new Vector2(
				(screenCoordinates.x * ImageEditor.Instance.ScreenSize.x) / Gdx.graphics.getWidth(),
				ImageEditor.Instance.ScreenSize.y
						- ((screenCoordinates.y) * ImageEditor.Instance.ScreenSize.y)
								/ Gdx.graphics.getHeight());
	}
}
