package tech.huning.wall.e.springboot.support.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import tech.huning.wall.e.springboot.support.util.SpringApplicationUtil;

/**
 * 应用工具类自动配置
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class SpringApplicationUtilAutoConfiguration implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringApplicationUtilAutoConfiguration.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(logger.isDebugEnabled()) {
            logger.debug("setApplicationContext:{}", applicationContext);
        }
        SpringApplicationUtil.setApplicationContext(applicationContext);
    }

}
