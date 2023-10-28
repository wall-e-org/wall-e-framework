package tech.huning.wall.e.util;

import tech.huning.wall.e.util.uo.CaptchaUO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * 图片验证码工具类
 * 1.使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CaptchaUtil {

	private static final String CAPTCHA_CHAR_SEEDS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	private static final String CAPTCHA_DIGITAL_SEEDS = "0123456789";
	private static Random random = new Random();

	/**
	 * 使用系统默认字符源生成验证码
	 * @param length  验证码长度
	 * @param onlyDigital 验证码只有数字
	 * @return 随机字符串
	 */
	public static String generateRandomCode(int length, boolean onlyDigital) {
		if(onlyDigital) {
			return generateRandomCode(length, CAPTCHA_DIGITAL_SEEDS);
		}else {
			return generateRandomCode(length, CAPTCHA_CHAR_SEEDS);
		}
	}

	/**
	 * 使用指定源生成验证码
	 * @param length 验证码长度
	 * @param seeds 验证码字符源
	 * @return 随机字符串
	 */
	public static String generateRandomCode(int length, String seeds) {
		int len = seeds.length();
		Random ran = new Random(System.currentTimeMillis());
		StringBuilder randomCode = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			randomCode.append(seeds.charAt(ran.nextInt(len - 1)));
		}
		return randomCode.toString();
	}

	/**
	 * 生成验证码
	 * @param width    宽度
	 * @param height   高度
	 * @param verifySize    长度
	 * @param onlyDigital   是否仅数字
	 * @param isBase64 是否base64
	 * @return 验证码
	 * @throws IOException IO异常
	 */
	public static CaptchaUO outputVerifyImage(Integer width, Integer height, Integer verifySize, Boolean onlyDigital, Boolean isBase64) throws IOException {
		return outputVerifyImage(width, height, verifySize, 300, onlyDigital,isBase64);
	}
	/**
	 * 输出随机验证码图片流,并返回验证码值
	 *
	 * @param width    宽度
	 * @param height   高度
	 * @param verifySize    长度
	 * @param expireIn 失效时间
	 * @param onlyDigital   是否仅数字
	 * @param isBase64 是否base64
	 * @return 验证码
	 * @throws IOException
	 */
	public static CaptchaUO outputVerifyImage(Integer width, Integer height, Integer verifySize, Integer expireIn, Boolean onlyDigital, Boolean isBase64) throws IOException {
		if(null == width) {
			width = 200;
		}
        if(null == height) {
        	height = 50;
		}
		if(null == verifySize) {
			verifySize = 6;
		}
        if(null == expireIn) {
        	expireIn = 300;
		}
        if(null == onlyDigital) {
        	onlyDigital = false;
		}
        if(null == isBase64) {
        	isBase64 = false;
		}
		String verifyCode = generateRandomCode(verifySize,onlyDigital);
		return outputImage(width, height, verifyCode, expireIn, isBase64);
	}

	public static CaptchaUO generate(Integer width, Integer height, Integer verifySize, Integer expireIn, Boolean onlyDigital, Boolean isBase64) throws IOException {
		return outputVerifyImage(width, height, verifySize, expireIn, onlyDigital, isBase64);
	}

	/**
	 * 输出指定验证码图片流
	 * @param w 宽
	 * @param h 高
	 * @param code 验证码
	 * @param expireIn 失效时间
	 * @param isBase64 是否base64
	 * @return 验证码
	 * @throws IOException IO异常
	 */
	public static CaptchaUO outputImage(int w, int h, String code, int expireIn, Boolean isBase64) throws IOException {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
				Color.ORANGE, Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, w, h);

		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, w, h - 4);

		// 绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		shear(g2, w, h, c);// 使图片扭曲

		g2.setColor(getRandColor(100, 160));
		int fontSize = h - 4;
		Font font = new Font("Algerian", Font.ITALIC, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
					(w / verifySize) * i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
		}

		g2.dispose();
		CaptchaUO dto = null;
		if(isBase64) {
			StringBuilder image64 = new StringBuilder();
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpeg", bos);
			byte[] imageBytes = bos.toByteArray();
			image64.append("data:image/jpeg;base64,");
	        image64.append(Base64.getEncoder().encodeToString(imageBytes));
	        bos.close();
			dto = new CaptchaUO(image64.toString(), code, expireIn, "jpeg");
		}else {
			dto = new CaptchaUO(image, code, expireIn, "jpeg");
		}
		return dto;
	}

	private static Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private static int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	private static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private static void shearX(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static void shearY(Graphics g, int w1, int h1, Color color) {
		int period = random.nextInt(40) + 10; // 50;
		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}
}
