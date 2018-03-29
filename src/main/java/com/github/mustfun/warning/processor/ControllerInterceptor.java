package com.github.mustfun.warning.processor;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2018/2/8
 * @since 1.0
 */
public class ControllerInterceptor implements MethodInterceptor, Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerInterceptor.class);


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOG.info("============ 自己定义的拦截器 ===========");
        String message = null;
        String url = null;
        try {
            Method method = invocation.getMethod();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            LOG.debug("Interceptor-User-Agent: {}", request.getHeader("User-Agent"));
            LOG.debug("Interceptor-Method: {}", method.getName());
            url = request.getRequestURL().toString();
            LOG.debug("Interceptor-URL: {}", url);
            message = JSON.toJSONString(request.getParameterMap());
            LOG.debug("Interceptor-parameters to: {}", message);
            return invocation.proceed();
        } catch (Throwable e) {
            LOG.info("ControllerIntercepter catch到异常......准备发送邮件{}", e);
            throw e;
        }
    }



}
