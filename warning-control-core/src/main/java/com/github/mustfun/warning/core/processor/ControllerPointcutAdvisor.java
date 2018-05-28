package com.github.mustfun.warning.core.processor;

import com.github.mustfun.warning.core.logging.Log;
import com.github.mustfun.warning.core.logging.LogFactory;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import javax.ws.rs.Path;
import java.lang.reflect.Method;

/**
 * @author itar
 * @version 1.0
 * @date 2018/2/8
 * @since 1.0
 */
public class ControllerPointcutAdvisor extends AbstractPointcutAdvisor{

    private static final Log LOG = LogFactory.getLog(ControllerPointcutAdvisor.class);

    private ControllerInterceptor controllerInterceptor;


    private final StaticMethodMatcherPointcut staticMethodMatcherPointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            if (targetClass.isAnnotationPresent(Path.class)) {
                LOG.info("captain-Advisor catch jax-rs "+targetClass.getSimpleName());
                return true;
            }
            boolean hashPath = false;
            for (Class<?> aClass : targetClass.getInterfaces()) {
                if (aClass.isAnnotationPresent(Path.class)) {
                    hashPath = true;
                }
            }
            if (hashPath) {
                LOG.info("captain-Advisor catch jax-rs "+targetClass.getSimpleName());
                return true;
            }
            if (targetClass.isAnnotationPresent(RequestMapping.class)) {
                LOG.info("captain-Advisor catch springMvc "+ targetClass.getSimpleName());
                return true;
            }
            return false;
        }
    };

    @Autowired
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
