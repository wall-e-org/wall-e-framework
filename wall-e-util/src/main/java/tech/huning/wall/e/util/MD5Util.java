package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密解密工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class MD5Util {

	/**
	 * MD5加密
	 * @param content    内容
	 * @param secretKey  密钥
	 * @return 秘文
	 * @throws SystemException 系统异常
	 */
	public static String encrypt(String content, String secretKey) throws SystemException {
		try {
			String encodeStr = DigestUtils.md5Hex(content + secretKey);
			return encodeStr;
		}catch(Exception e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
	}

	/**
	 * MD5验证
	 * @param content    内容
	 * @param secretKey  密钥
	 * @param md5        md5码
	 * @return 是否有效
	 * @throws SystemException 系统异常
	 */
	public static boolean verify(String content, String secretKey, String md5) throws SystemException {
		try {
			if (md5.equals(encrypt(content, secretKey))) {
				return true;
			}
			return false;
		}catch(Exception e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
	}
	
}
