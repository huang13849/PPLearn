package com.hqb.pplearn.common.util;

import java.util.Random;

public class RandomUtil {
	public static String getRandomNumber(int len) {
		StringBuffer sbRandomString = new StringBuffer();
		Random random = new Random(System.currentTimeMillis());

		for (int i = 0; i < len; i++) {
			sbRandomString.append(random.nextInt(9));
		}
		return sbRandomString.toString();
	}
}
