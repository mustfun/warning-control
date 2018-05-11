package com.github.mustfun.warning.core;

import com.github.mustfun.warning.core.processor.ControllerInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2018/5/11
 * @since 1.0
 */
public class ControllerInterceptorTest {

    @Test
    public void testControllerInterceptor(){
        ControllerInterceptor controllerInterceptor = new ControllerInterceptor();
        try {
            controllerInterceptor.invoke(new MethodInvocation() {
                @Override
                public Method getMethod() {
                    return ControllerInterceptor.class.getMethods()[0];
                }

                @Override
                public Object[] getArguments() {
                    return new Object[0];
                }

                @Override
                public Object proceed() throws Throwable {
                    return null;
                }

                @Override
                public Object getThis() {
                    return null;
                }

                @Override
                public AccessibleObject getStaticPart() {
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
