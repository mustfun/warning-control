package com.github.mustfun.warning.core.processor;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import javax.ws.rs.Path;
import java.lang.reflect.Method;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2018/2/8
 * @since 1.0
 */
public class ControllerPointcutAdvisor extends AbstractPointcutAdvisor{

    private static final Logger LOG = LoggerFactory.getLogger(ControllerPointcutAdvisor.class);

    @Autowired
    private ControllerInterceptor controllerInterceptor;


    private final StaticMethodMatcherPointcut staticMethodMatcherPointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            if (targetClass.isAnnotationPresent(Path.class)) {
                LOG.info("captain-Advisor catch jax-rs {}", targetClass.getSimpleName());
                return true;
            }
            boolean hashPath = false;
            for (Class<?> aClass : targetClass.getInterfaces()) {
                if (aClass.isAnnotationPresent(Path.class)) {
                    hashPath = true;
                }
            }
            if (hashPath) {
                LOG.info("captain-Advisor catch jax-rs {}", targetClass.getSimpleName());
                return true;
            }
            if (targetClass.isAnnotationPresent(RequestMapping.class)) {
                LOG.info("captain-Advisor catch springMvc {}", targetClass.getSimpleName());
                return true;
            }
            return false;
        }
    };

    public ControllerPointcutAdvisor(ControllerInterceptor controllerInterceptor) {
        this.controllerInterceptor = controllerInterceptor;
    }

    public ControllerPointcutAdvisor() {
    }

    @Override
    public Pointcut getPointcut() {
        return this.staticMethodMatcherPointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.controllerInterceptor;
    }
}
