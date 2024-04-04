package com.mygdx.image_editor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CollisionManager {

	public static CollisionManager Instance;

	public CollisionManager() {
		Instance = this;
	}

	public Array<Button> getCollision(Vector2 coordinates) {
		Array<Button> Collisions = new Array<Button>();
		for (Button button : InputManager.Instance.Buttons) {
			if (coordinates.x >= button.Position.x &&
					coordinates.x <= button.Position.x + button.Scale.x &&
					coordinates.y >= button.Position.y &&
					coordinates.y <= button.Position.y + button.Scale.y) {
				Collisions.add(button);
			}
		}
		return Collisions;
	}
}
