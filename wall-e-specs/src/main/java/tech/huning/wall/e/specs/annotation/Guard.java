package tech.huning.wall.e.specs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 卫兵标签
 * 1.限制接口访问频率
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Guard {

    // API请求次数
    int times() default 4;

    // 记录周期(秒数)
    int period() default 10;

}
