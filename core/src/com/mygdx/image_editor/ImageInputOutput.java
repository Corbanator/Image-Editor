package com.mygdx.image_editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class ImageInputOutput {
	public static ImageInputOutput Instance;

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

		return pixels;
	}
}
