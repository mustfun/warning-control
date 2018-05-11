package com.github.mustfun.warning.core;

import com.github.mustfun.warning.core.processor.ControllerInterceptor;
import org.junit.Test;

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
            controllerInterceptor.invoke(null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
