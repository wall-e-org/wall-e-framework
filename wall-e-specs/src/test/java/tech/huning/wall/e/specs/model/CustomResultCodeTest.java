package tech.huning.wall.e.specs.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * 结果码测试
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CustomResultCodeTest {

    @Test
    public void test() {
        Assert.assertEquals("user_invalid", CustomResultCode.USER_INVALID.getCode());
        Assert.assertEquals("用户无效", CustomResultCode.USER_INVALID.getMsg());

        Assert.assertEquals("success", CustomResultCode.SUCCESS.getCode());
        Assert.assertEquals("成功", CustomResultCode.SUCCESS.getMsg());
    }

    public enum CustomResultCode implements ResultCode {

        USER_INVALID("user_invalid", "用户无效");

        private final String code;
        private final String msg;

        CustomResultCode(String code, String msg) {
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

}
