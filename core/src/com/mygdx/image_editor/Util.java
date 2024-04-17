package com.mygdx.image_editor;

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
}
