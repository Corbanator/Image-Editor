package com.mygdx.image_editor;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.image_editor.ImageEditor;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("ImageEditor");
		int height = 480;
		int width = 584;
		config.setWindowedMode(width, height);
		ImageEditor editor = new ImageEditor(width, height);
		config.setWindowListener(new Lwjgl3WindowAdapter() {
			public void filesDropped(String[] files) {
				editor.filesImported(files);
			}
		});
		new Lwjgl3Application(editor, config);
	}
}
