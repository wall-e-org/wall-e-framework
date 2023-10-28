package tech.huning.wall.e.util.uo;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码模型 UtilObject
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CaptchaUO {

    private BufferedImage image;       // 图片文件流
    private String image64;            // 图片base64字符串
    private String type;               // 图片类型,如"jpeg"
	private String code;               // 验证码字符内容
	private LocalDateTime expireTime;  // 有效期限
	
	public CaptchaUO(BufferedImage image, String code, int expireIn, String type) {
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
		this.type = type;
	}
	
	public CaptchaUO(String image64, String code, int expireIn, String type) {
		this.image64 = image64;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
		this.type = type;
	}
	
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
	public String getImage64() {
		return image64;
	}

	public void setImage64(String image64) {
		this.image64 = image64;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
