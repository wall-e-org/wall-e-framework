package tech.huning.wall.e.util.os;

/**
 * 系统工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class SystemUtil {

    /**
     * 是否是Windows系统
     * @return true/false
     */
    public static boolean isWindows() {
        String osName = getOsName();
        return osName != null && osName.startsWith("Windows");
    }

    /**
     * 是否是MacOS系统
     * @return true/false
     */
    public static boolean isMacOs() {
        String osName = getOsName();
        return osName != null && osName.startsWith("Mac");
    }

    /**
     * 是否是Linux系统
     * @return true/false
     */
    public static boolean isLinux() {
        String osName = getOsName();
        return (osName != null && osName.startsWith("Linux")) || (!isWindows() && !isMacOs());
    }

    /**
     * 获取用户目录
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    private static String getOsName() {
        return System.getProperty("os.name");
    }

}
