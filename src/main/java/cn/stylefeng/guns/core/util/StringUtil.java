package cn.stylefeng.guns.core.util;

public class StringUtil {

	/**
	 * 给定字符串是否为空或空串
	 *
	 * @param str 字符串
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.length() != 0;
	}

	/**
	 * 给定字符串是否为空或空串
	 *
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	public static String[] split(String propertyName, String regex) {
		return propertyName.split("\\" + regex);
	}

	public static String capitalize(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}
