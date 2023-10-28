package tech.huning.wall.e.springboot.support.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import tech.huning.wall.e.springframework.support.log.LogConfig;
import tech.huning.wall.e.springframework.support.log.LogProcessor;

/**
 * 日志自动配置
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@ConditionalOnClass(ProceedingJoinPoint.class)
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfiguration {

    private final Logger logger = LoggerFactory.getLogger(LogAutoConfiguration.class);

    @Bean(LogProcessor.BEAN_ID)
    @ConditionalOnProperty(prefix = LogConfig.PREFIX, name = "enabled", havingValue = "true")
    public LogProcessor processor(LogProperties logProperties) {
        if(logger.isDebugEnabled()) {
            logger.debug("LogProcessor Loading");
        }
        return new LogProcessor(logProperties);
    }

}
