package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.util.constant.UtilResultCode;
import org.junit.Assert;
import org.junit.Test;

/**
 * AES工具类测试
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class AESUtilTest {

    @Test
    public void testNormal() throws SystemException {
        String plainText = "A simple plain text";
        String secretKey = "1234567890123456";
        String cipherText = AESUtil.encrypt(plainText, secretKey);
        Assert.assertEquals(plainText, AESUtil.decrypt(cipherText, secretKey));
    }

    @Test
    public void testEmptyPlainText() {
        try{
            String plainText = "";
            String secretKey = "1234567890123456";
            AESUtil.encrypt(plainText, secretKey);
        } catch (SystemException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(UtilResultCode.AES_PLAIN_TEXT_IS_EMPTY.getCode(), e.getCode());
            Assert.assertEquals(UtilResultCode.AES_PLAIN_TEXT_IS_EMPTY.getMsg(), e.getMsg());
        }
    }

    @Test
    public void testEmptyCipherText() {
        try{
            String cipherText = "";
            String secretKey = "1234567890123456";
            AESUtil.decrypt(cipherText, secretKey);
        } catch (SystemException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(UtilResultCode.AES_CIPHER_TEXT_IS_EMPTY.getCode(), e.getCode());
            Assert.assertEquals(UtilResultCode.AES_CIPHER_TEXT_IS_EMPTY.getMsg(), e.getMsg());
        }
    }

    @Test
    public void testEmptySecretKey() {
        try{
            String plainText = "A simple plain text";
            String secretKey = "";
            String cipherText = AESUtil.encrypt(plainText, secretKey);
            Assert.assertEquals(plainText, AESUtil.decrypt(cipherText, secretKey));
        } catch (SystemException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(UtilResultCode.AES_SECRET_KEY_IS_EMPTY.getCode(), e.getCode());
            Assert.assertEquals(UtilResultCode.AES_SECRET_KEY_IS_EMPTY.getMsg(), e.getMsg());
        }
    }

    @Test
    public void testInvalidSecretKey() {
        try{
            String plainText = "A simple plain text";
            String secretKey = "123456";
            String cipherText = AESUtil.encrypt(plainText, secretKey);
            Assert.assertEquals(plainText, AESUtil.decrypt(cipherText, secretKey));
        } catch (SystemException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(UtilResultCode.AES_SECRET_KEY_LENGTH_INVALID.getCode(), e.getCode());
            Assert.assertEquals(UtilResultCode.AES_SECRET_KEY_LENGTH_INVALID.getMsg(), e.getMsg());
        }
    }

}
