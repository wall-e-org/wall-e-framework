package tech.huning.wall.e.util;

/**
 * 随机内容工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class RandomUtil {

	private static final String CHAR_SEEDS  = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%_";

	/**
	 * 获取随机数字
	 * @param range 范围
	 * @return 随机数
	 */
	public static int getInt(int range) {
		return new Double(Math.floor(Math.random() * range)).intValue();
	}

	/**
	 * 获取随机字符串
	 * @param length 长度
	 * @return 随机字符串
	 */
	public static String getString(int length) {
		StringBuilder sb = new StringBuilder();
		int range = CHAR_SEEDS.length();
		for (int i = 0; i < length; i++) {
			sb.append(CHAR_SEEDS.charAt(getInt(range)));
		}
		return sb.toString();
	}
	
}
