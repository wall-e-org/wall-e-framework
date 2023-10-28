package tech.huning.wall.e.util;

import java.util.UUID;

/**
 * ID工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class IdUtil {

    /**
     * 获取UUID
     * @return UUID
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

}
