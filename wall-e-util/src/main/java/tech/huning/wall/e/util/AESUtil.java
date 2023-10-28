package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;
import tech.huning.wall.e.util.constant.UtilResultCode;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES加密工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class AESUtil {

    // 加密算法
    private static final String KEY_ALGORITHM = "AES";
    // 默认的加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密明文
     * @param plainText  明文
     * @param secretKey  密钥
     * @return 密文
     * @throws SystemException 系统异常
     */
    public static String encrypt(String plainText, String secretKey) throws SystemException {
    	try {
    	    // 校验
            if(StringUtil.isEmpty(plainText)) {
                throw new SystemException(UtilResultCode.AES_PLAIN_TEXT_IS_EMPTY);
            }
            checkSecretKey(secretKey);

            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), KEY_ALGORITHM);

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);

            // 加密
            byte[] result = cipher.doFinal(bytes);

            // 通过Base64转码返回
            return Base64.encodeBase64String(result);
    	} catch (SystemException e) {
    	    throw e;
        } catch(Exception e) {
    		throw new SystemException(e, ResultCode.FAILURE);
    	}
    }

    /**
     * 解密密文
     * @param cipherText 密文
     * @param secretKey  密钥
     * @return 明文
     * @throws SystemException 系统异常
     */
    public static String decrypt(String cipherText, String secretKey) throws SystemException{
    	try {
            // 校验
            if(StringUtil.isEmpty(cipherText)) {
                throw new SystemException(UtilResultCode.AES_CIPHER_TEXT_IS_EMPTY);
            }
            checkSecretKey(secretKey);

            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), KEY_ALGORITHM);

            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // 解密
            byte[] bytes = cipher.doFinal(Base64.decodeBase64(cipherText));

            return new String(bytes, StandardCharsets.UTF_8);
    	} catch (SystemException e) {
            throw e;
        } catch(Exception e) {
            throw new SystemException(e, ResultCode.FAILURE);
        }
    }

    private static void checkSecretKey(String secretKey) throws SystemException {
        if(StringUtil.isEmpty(secretKey)) {
            throw new SystemException(UtilResultCode.AES_SECRET_KEY_IS_EMPTY);
        }
        if(secretKey.getBytes().length != 16) {
            throw new SystemException(UtilResultCode.AES_SECRET_KEY_LENGTH_INVALID);
        }
    }

}
