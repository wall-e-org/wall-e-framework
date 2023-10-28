package tech.huning.wall.e.util;

/**
 * 字符串工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class StringUtil {

    public static boolean isEmpty(String source) {
        if(null == source) {
            return true;
        }
        return source.isEmpty();
    }

    public static boolean isNotEmpty(String source) {
        return !isEmpty(source);
    }

    public static boolean isEqual(String source, String destination) {
        if(null == source) {
            return null == destination;
        }
        return source.equals(destination);
    }

    public static boolean isNotEqual(String source, String destination) {
        return !isEqual(source, destination);
    }

}
