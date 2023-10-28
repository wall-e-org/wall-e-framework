package tech.huning.wall.e.util;

import org.junit.Test;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * 随机工具类测试
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class RandomUtilTest {

    @Test
    public void test() {
        Logger.getGlobal().info(RandomUtil.getString(64));
        Logger.getGlobal().info(UUID.randomUUID().toString().replaceAll("-", ""));
    }

}
