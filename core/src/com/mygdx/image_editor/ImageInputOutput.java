package com.mygdx.image_editor;

import java.io.FileOutputStream;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;

public class ImageInputOutput {
	public static ImageInputOutput Instance;

	private byte[] _fileHeader;
	private Pixmap _pixels;
	public String ImageFolderLocation;

	public ImageInputOutput() {
		Instance = this;
	}

	public Pixmap loadImage(String filePath) {
		byte[] bytes = Gdx.files.internal(filePath).readBytes();
		int[] ints = Util.unsignBytes(bytes);

		if (ints[0] != 'B' || ints[1] != 'M') {
			System.out.println(filePath + " is NOT a bitmap image");
			return null;
		}

		int[] fileSize = { ints[2], ints[3], ints[4], ints[5] };
		int[] start = { ints[10], ints[11], ints[12], ints[13] };
		int[] widthBytes = { ints[18], ints[19], ints[20], ints[21] };
		int[] heightBytes = { ints[22], ints[23], ints[24], ints[25] };
		int[] bitsPerPixel = { ints[28], ints[29] };
		int startPoint = Util.bytesToInt(start);
		int width = Util.bytesToInt(widthBytes);
		int height = Util.bytesToInt(heightBytes);
		int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
		_fileHeader = new byte[startPoint];
		for (int i = 0; i < startPoint; i++) {
			_fileHeader[i] = bytes[i];
		}

		if (bytesPerPixel != 3) {
			System.out.println("Unsupported image pixel format. Incorrect bits per pixel");
			return null;
		}

		Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
		int r, g, b;
		int x, y;
		x = 0;
		y = height;
		for (int i = startPoint; i < ints.length - 3; i += 3) {
			b = ints[i];
			g = ints[i + 1];
			r = ints[i + 2];

			pixels.setColor(Color.rgba8888(((float) r) / 256, ((float) g) / 256, ((float) b) / 256, 1f));
			pixels.drawPixel(x, y);

			x++;
			if (x >= width) {
				x = 0;
				y--;
			}
		}
		_pixels = pixels;

		return pixels;
	}

	public void saveImage(String filePath) throws IOException {
		FileOutputStream output = new FileOutputStream(filePath);

		byte[] color;
		byte[] colorData = new byte[_pixels.getWidth() * _pixels.getHeight() * 3];
		int colorIndex = 0;
		for (int y = _pixels.getHeight() - 1; y >= 0; y--) {
			for (int x = 0; x < _pixels.getWidth(); x++) {
				color = Util.intToSignedBytes(_pixels.getPixel(x, y));
				colorData[colorIndex] = color[2];
				colorData[colorIndex + 1] = color[1];
				colorData[colorIndex + 2] = color[0];
				colorIndex += 3;

			}
		}

		// The loop that grabs data from _pixels
		Pixmap doodle = Util.scalePixmap(
				EditWindow.Instance.DoodleMap, new Vector2(_pixels.getWidth(), _pixels.getHeight()));
		colorIndex = 0;
		for (int y = doodle.getHeight() - 1; y >= 0; y--) {
			for (int x = 0; x < doodle.getWidth(); x++) {
				color = Util.intToSignedBytes(doodle.getPixel(x, y));
				if (color[3] != -1) {
					colorIndex += 3;
					continue;
				}
				colorData[colorIndex] = color[2];
				colorData[colorIndex + 1] = color[1];
				colorData[colorIndex + 2] = color[0];
				colorIndex += 3;
			}
		}

		output.write(_fileHeader);
		output.write(colorData);

		output.close();
	}

	private String scrapeFolderLocation(String filePath) {
		StringBuilder builder = new StringBuilder(filePath);
		for (int i = filePath.length() - 1; i >= 0; i--) {
			if (filePath.charAt(i) != '/')
				continue;
			return builder.substring(0, i);
		}
		return null;
	}
}
