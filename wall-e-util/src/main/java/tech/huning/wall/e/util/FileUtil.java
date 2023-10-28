package tech.huning.wall.e.util;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * 文件处理工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class FileUtil {

    public static void deleteFiles(File file){
        if(null == file || !file.exists()) {
            return;
        }
        if(file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(FileUtil::deleteFiles);
        }
        file.delete();
    }

}
