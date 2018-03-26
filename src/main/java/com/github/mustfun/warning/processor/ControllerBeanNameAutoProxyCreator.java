package com.github.mustfun.warning.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.aop.framework.autoproxy.AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE;

/**
 * @author : itar
 * @date : 2018-03-25
 * @time : 22:23
 * @Version: 1.0
 * @since: JDK 1.8
 */
@Component
public class ControllerBeanNameAutoProxyCreator extends BeanNameAutoProxyCreator implements BeanFactoryPostProcessor {

   private static final Logger LOG = LoggerFactory.getLogger(ControllerBeanNameAutoProxyCreator.class);

   private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
       this.beanFactory = beanFactory;
       super.setBeanFactory(beanFactory);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Class<?> aClass : bean.getClass().getInterfaces()) {
            LOG.info("实例化的类的接口是====="+aClass.getName()+"====当前类是"+beanName);
        }
        if (beanName.contains("city")||beanName.contains("City")){
            LOG.info("CITY接口是====="+beanName);
            ConfigurableListableBeanFactory configurableListableBeanFactory = (ConfigurableListableBeanFactory)beanFactory;
            BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition(beanName);
            bd.setAttribute(PRESERVE_TARGET_CLASS_ATTRIBUTE,Boolean.TRUE);
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
