package com.github.mustfun.warning.core.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2018/3/29
 * @since 1.0
 */
public class WarningControlPostProcessor implements BeanDefinitionRegistryPostProcessor,InitializingBean, BeanNameAware {

    private static final Logger LOG = LoggerFactory.getLogger(WarningControlPostProcessor.class);

    /**
     * 生成bean名称用
     */
    private BeanNameGenerator nameGenerator;

    private String beanName;


    /**
     * reference doc : https://blog.csdn.net/elim168/article/details/78056446
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //是否使用默认的filter，使用默认的filter意味着只扫描那些类上拥有Component、Service、Repository或Controller注解的类
        //boolean useDefaultFilters = false;
        boolean useDefaultFilters = true;
        String basePackage = "com.github.mustfun.warning.core.processor";
        ClassPathScanningCandidateComponentProvider beanScanner = new ClassPathScanningCandidateComponentProvider(useDefaultFilters);
        Set<BeanDefinition> beanDefinitions = beanScanner.findCandidateComponents(basePackage);
        for (BeanDefinition rowBeanDefinition : beanDefinitions) {
            ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition)rowBeanDefinition;
            //beanName通常由对应的BeanNameGenerator来生成，比如Spring自带的AnnotationBeanNameGenerator、DefaultBeanNameGenerator等，也可以自己实现。
            String beanName = beanDefinition.getBeanClassName();
            beanDefinition.setAutowireCandidate(true);
            beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
            beanDefinition.setScope("singleton");
            registry.registerBeanDefinition(beanName, beanDefinition);

        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void setBeanName(String name) {
        LOG.info("捕捉到的bean名称是"+name);
        this.beanName = name;
    }
}
