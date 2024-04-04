package com.mygdx.image_editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
	public static InputManager Instance;

	public Array<Button> Buttons = new Array<Button>();

	public InputManager() {
		Instance = this;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Array<Button> collisions = CollisionManager.Instance
				.getCollision(convertToWorldCoordinates(
						new Vector2(screenX, screenY)));
		for (Button collision : collisions) {
			collision.onClickDown();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for (Button Button : Buttons) {
			if (Button.Clicked) {
				Button.onClickUp();
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
		return mouseMoved(screenX, screenY);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Array<Button> collisions = CollisionManager.Instance
				.getCollision(convertToWorldCoordinates(
						new Vector2(screenX, screenY)));
		for (Button collision : collisions) {
			if (!collision.Hovered) {
				collision.onHovered();
			}
		}
		for (Button button : Buttons) {
			if (!collisions.contains(button, true)) {
				if (button.Hovered) {
					button.onHoverExit();
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
