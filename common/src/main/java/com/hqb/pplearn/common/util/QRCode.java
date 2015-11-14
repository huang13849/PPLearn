package com.hqb.pplearn.common.util;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCode {
	@SuppressWarnings("unchecked")
	public static void encode(String content, int width, int height, OutputStream stream) {
		try {

			String format = "png";

			@SuppressWarnings("rawtypes")
			HashMap hints = new HashMap();
			// 内容所使用编码
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter.writeToStream(matrix, format, stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		createCode();
		System.out.println("Done2");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createCode() {
		String text = "http://www.cnblogs.com";
		int width = 300;
		int height = 300;
		// 二维码的图片格式
		String format = "png";
		/**
		 * 设置二维码的参数
		 */
		HashMap hints = new HashMap();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码
			File outputFile = new File("D:" + File.separator + "tem" + File.separator + "TDC-test.png");
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
