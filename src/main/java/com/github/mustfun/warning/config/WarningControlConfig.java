package com.github.mustfun.warning.config;

import com.github.mustfun.warning.processor.WarningControlContextInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2018/3/29
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties
public class WarningControlConfig {

    private static Logger logger = LoggerFactory.getLogger(WarningControlConfig.class);

    public WarningControlConfig() {
        logger.info("我就看看调用过没有......");
    }

    @Bean
    @Qualifier("warningControlContextInitializer")
    public static WarningControlContextInitializer warningControlContextInitializer() {
        return new WarningControlContextInitializer();
    }

}
