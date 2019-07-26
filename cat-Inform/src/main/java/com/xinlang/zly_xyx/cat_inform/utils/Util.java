package com.xinlang.zly_xyx.cat_inform.utils;

import java.util.Random;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
public class Util {

	private static String[] NUMBERS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static Random RANDOM = new Random();

	/**
	 * 生成n位随机数值字符串
	 * 
	 * @param n
	 * @return
	 */
	public static String randomCode(int n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			builder.append(NUMBERS[RANDOM.nextInt(NUMBERS.length)]);
		}

		return builder.toString();
	}
}
