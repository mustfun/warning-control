package com.github.mustfun.warning.core.processor;

import com.github.mustfun.warning.core.logging.Log;
import com.github.mustfun.warning.core.logging.LogFactory;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : itar
 * @date : 2018-03-25
 * @time : 22:23
 * @Version: 1.0
 * @since: JDK 1.8
 */
public class ControllerBeanNameAutoProxyCreator extends BeanNameAutoProxyCreator implements BeanFactoryPostProcessor {

   private static final Log LOG = LogFactory.getLog(ControllerBeanNameAutoProxyCreator.class);

   private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
       this.beanFactory = beanFactory;
       super.setBeanFactory(beanFactory);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Class<?> aClass : bean.getClass().getInterfaces()) {
            LOG.info("实例化的类的接口是=====" + aClass.getName() + "====当前类是" + beanName);
        }

        return super.postProcessAfterInitialization(bean, beanName);
    }




    @Override
    public void postProcessBeanFactory(
            ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        String[] beanNamesForAnnotation = beanFactory.getBeanNamesForAnnotation(RequestMapping.class);
        for (String beanName : beanNamesForAnnotation) {
            LOG.info("find SpringMvc annotation in project, change RequestMapping annotation to be proxied with its target class,not a interface");
            BeanDefinition beanDef = beanFactory.getBeanDefinition(beanName);
            beanDef.setAttribute(AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, Boolean.TRUE);
        }
    }

}
