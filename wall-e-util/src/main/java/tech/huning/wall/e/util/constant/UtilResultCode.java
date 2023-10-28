package tech.huning.wall.e.util.constant;

import tech.huning.wall.e.specs.model.ResultCode;

/**
 * 工具箱结果码
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public enum  UtilResultCode implements ResultCode {

    /**登陆注销*/
    USER_NOT_EXIST("user_not_exist","用户不存在!"),
    USER_ACCT_PWD_IS_NOT_MATCHED("user_acct_pwd_is_not_matched","账号或密码错误!"),
    USER_LOGOUT_OR_NOT_LOGIN("user_logout_or_not_login","用户注销或未登录!"),

    /**验证码*/
    CAPTCHA_CAN_NOT_BE_EMPTY("captcha_can_not_be_empty","验证码不能为空!"),
    CAPTCHA_IS_EXPIRED("captcha_is_expired","验证码过期!"),
    CAPTCHA_IS_NOT_MATCHED("captcha_is_not_matched","验证码错误!"),
    CAPTCHA_VERIFY_FAILURE("captcha_verify_failure","验证图形验证码!"),

    /**Token相关*/
    JWT_TOKEN_EXPIRED("jwt_token_expired","JwtToken过期!"),
    GENERATE_TOKEN_FAILURE("generate_token_failure", "生成Token失败!"),
    VERIFY_TOKEN_CAN_NOT_BE_EMPTY("verify_token_can_not_be_empty", "验证Token不能为空!"),
    VERIFY_TOKEN_EXPIRED("verify_token_expired", "验证Token过期!"),
    VERIFY_TOKEN_FAILURE("verify_token_failure", "验证Token失败!"),

    AES_PLAIN_TEXT_IS_EMPTY("aes_plaintext_is_empty", "AES明文内容不能为空!"),
    AES_CIPHER_TEXT_IS_EMPTY("aes_cipherText_is_empty", "AES密文内容不能为空!"),
    AES_SECRET_KEY_IS_EMPTY("aes_secret_Key_is_empty", "AES秘钥内容不能为空!"),
    AES_SECRET_KEY_LENGTH_INVALID("aes_secret_key_length_invalid", "AES秘钥长度必须为16字节!"),

    MAKE_DIRECTORY_FAILURE("make_directory_failure", "创建文件夹失败!"),
    GENERATE_THUMBNAILS_FAILURE("generate_thumbnails_failure", "生成缩略图失败!"),
    SCALE_MUST_BE_POSITIVE("scale_must_be_positive", "精度必须为正整数!");

    private final String code;
    private final String msg;

    UtilResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

}
