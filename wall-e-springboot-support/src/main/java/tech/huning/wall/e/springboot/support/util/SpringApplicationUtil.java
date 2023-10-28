package tech.huning.wall.e.springboot.support.util;

import org.springframework.context.ApplicationContext;

import java.util.Objects;

/**
 * Spring应用工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class SpringApplicationUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext){
        if(Objects.isNull(SpringApplicationUtil.applicationContext)) {
            SpringApplicationUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return SpringApplicationUtil.applicationContext;
    }

}
