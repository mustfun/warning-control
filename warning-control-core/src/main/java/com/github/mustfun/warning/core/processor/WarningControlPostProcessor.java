package com.github.mustfun.warning.core.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
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
public class WarningControlPostProcessor implements BeanDefinitionRegistryPostProcessor,InitializingBean {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //是否使用默认的filter，使用默认的filter意味着只扫描那些类上拥有Component、Service、Repository或Controller注解的类
        boolean useDefaultFilters = false;
        String basePackage = "com.github.mustfun.warning.core.processor";
        ClassPathScanningCandidateComponentProvider beanScanner = new ClassPathScanningCandidateComponentProvider(useDefaultFilters);
        TypeFilter includeFilter = new TypeFilter() {

            //非接口和非抽象类
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                    throws IOException {
                return metadataReader.getClassMetadata().isConcrete();
            }

        };
        beanScanner.addIncludeFilter(includeFilter);
        Set<BeanDefinition> beanDefinitions = beanScanner.findCandidateComponents(basePackage);
        for (BeanDefinition rawBeanDefinition : beanDefinitions) {
            //beanName通常由对应的BeanNameGenerator来生成，比如Spring自带的AnnotationBeanNameGenerator、DefaultBeanNameGenerator等，也可以自己实现。
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition)rawBeanDefinition;
            String beanName = beanDefinition.getBeanClassName();
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            beanDefinition.setAutowireCandidate(true);
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
