package tech.huning.wall.e.springboot.support.cors;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import tech.huning.wall.e.specs.constant.SymbolConstant;

/**
 * 跨域自动配置
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@EnableConfigurationProperties(CorsProperties.class)
@ConditionalOnProperty(prefix = CorsProperties.PREFIX, name = "enabled", havingValue = "true")
public class CorsAutoConfiguration {

    private final Logger logger = LoggerFactory.getLogger(CorsAutoConfiguration.class);

    @Bean
    public CorsFilter corsFilter(CorsProperties corsProperties) {

        if(logger.isDebugEnabled()) {
            logger.debug("CorsFilter Loading, corsProperties:{}", JSON.toJSONString(corsProperties));
        }

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        if(CollectionUtils.isEmpty(corsProperties.getAllowedHeaders())) {
            corsConfiguration.addAllowedHeader(SymbolConstant.ASTERISK);
        }else{
            corsConfiguration.setAllowedHeaders(corsProperties.getAllowedHeaders());
        }

        if(CollectionUtils.isEmpty(corsProperties.getAllowedMethods())) {
            corsConfiguration.addAllowedMethod(SymbolConstant.ASTERISK);
        }else{
            corsConfiguration.setAllowedMethods(corsProperties.getAllowedHeaders());
        }

        if(CollectionUtils.isEmpty(corsProperties.getAllowedOrigins())) {
            corsConfiguration.addAllowedOrigin(SymbolConstant.ASTERISK);
        }else{
            corsConfiguration.setAllowedOrigins(corsProperties.getAllowedHeaders());
        }

        corsConfiguration.setMaxAge(corsProperties.getMaxAge());
        corsConfiguration.setAllowCredentials(corsProperties.getAllowCredentials());
        source.registerCorsConfiguration(corsProperties.getPath(), corsConfiguration);
        return new CorsFilter(source);
    }

}
