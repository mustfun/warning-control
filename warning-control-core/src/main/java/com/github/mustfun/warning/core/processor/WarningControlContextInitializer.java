package com.github.mustfun.warning.core.processor;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author itar
 * @version 1.0
 * @date 2018/3/29
 * @since 1.0
 */
public class WarningControlContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        context.addBeanFactoryPostProcessor(
                new WarningControlPostProcessor());
    }
}
