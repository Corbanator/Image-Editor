package com.mygdx.image_editor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CollisionManager {

	public static CollisionManager Instance;

	public CollisionManager() {
		Instance = this;
	}

	public Array<IClickable> getClicked(Vector2 coordinates) {
		Array<IClickable> Collisions = new Array<IClickable>();
		for (IClickable clickable : InputManager.Instance.Clickables) {
			if (coordinates.x >= ((Rec2D) clickable).Position.x &&
					coordinates.x <= ((Rec2D) clickable).Position.x + ((Rec2D) clickable).Scale.x &&
					coordinates.y >= ((Rec2D) clickable).Position.y &&
					coordinates.y <= ((Rec2D) clickable).Position.y + ((Rec2D) clickable).Scale.y) {
				Collisions.add(clickable);
			}
		}
		return Collisions;
	}

	public Array<IHoverable> getHovered(Vector2 coordinates) {
		Array<IHoverable> Collisions = new Array<IHoverable>();
		for (IHoverable hoverable : InputManager.Instance.Hoverables) {
			if (coordinates.x >= ((Rec2D) hoverable).Position.x &&
					coordinates.x <= ((Rec2D) hoverable).Position.x + ((Rec2D) hoverable).Scale.x &&
					coordinates.y >= ((Rec2D) hoverable).Position.y &&
					coordinates.y <= ((Rec2D) hoverable).Position.y + ((Rec2D) hoverable).Scale.y) {
				Collisions.add(hoverable);
			}
		}
		return Collisions;
	}
}
