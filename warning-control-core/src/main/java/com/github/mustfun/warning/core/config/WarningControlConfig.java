package com.github.mustfun.warning.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author itar
 * @version 1.0
 * @date 2018/3/29
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties
public class WarningControlConfig {

    private static Logger logger = LoggerFactory.getLogger(WarningControlConfig.class);

    public WarningControlConfig() {
        logger.info("warning control config has bean called");
    }

}
