package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(int[] bytes) {
		int result = 0;
		for (int i = 0; i < bytes.length; i++) {
			result += (int) bytes[i] << (8 * i);
		}
		return result;
	}

	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] >= 0) {
				ints[i] = bytes[i];
			} else {
				ints[i] = bytes[i] + 256;
			}
		}
		return ints;
	}

	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		for (int i = 0; i < 4; i++) {
			result[i] = (byte) ((value << (i * 8)) >> 24);
		}
		return result;
	}

	public static void testIntToSignedBytes() {
		byte[] testResults = intToSignedBytes(543152314);
		int[] expectedResults = { 32, 95, -40, -70 };
		for (int i = 0; i < testResults.length; i++) {
			if ((int) testResults[i] != expectedResults[i])
				System.out.println("TEST FAILED! INDEX " + i + " IS "
						+ testResults[i] + " EXPECTED: " + expectedResults[i]);
		}
	}

	public static Pixmap scalePixmap(Pixmap map, Vector2 desiredSize) {
		Pixmap newMap = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		int width = map.getWidth();
		int height = map.getHeight();
		for (int x = 0; x < newMap.getWidth(); x++) {
			for (int y = 0; y < newMap.getHeight(); y++) {
				int sourceX = x * width / (int) desiredSize.x;
				int sourceY = y * height / (int) desiredSize.y;
				newMap.setColor(map.getPixel(sourceX, sourceY));
				newMap.drawPixel(x, y);
			}
		}
		return newMap;
	}
}
