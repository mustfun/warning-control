package com.github.mustfun.warning.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import java.lang.annotation.Annotation;


/**
 * 一个 {#code BeanDefinitionParser}处理注入的
 * @author itar
 */
public class WarningBeanDefinitionParser implements BeanDefinitionParser {

  private static final Logger LOG = LoggerFactory.getLogger(WarningBeanDefinitionParser.class);

  private static final String ATTRIBUTE_BASE_PACKAGE = "base-package";
  private static final String ATTRIBUTE_ANNOTATION = "annotation";


  @Override
  public synchronized BeanDefinition parse(Element element, ParserContext parserContext) {
    ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(parserContext.getRegistry());
    ClassLoader classLoader = scanner.getResourceLoader().getClassLoader();
    XmlReaderContext readerContext = parserContext.getReaderContext();
    scanner.setResourceLoader(readerContext.getResourceLoader());
    try {
      String annotationClassName = element.getAttribute(ATTRIBUTE_ANNOTATION);
      if (StringUtils.hasText(annotationClassName)) {
        @SuppressWarnings("unchecked")
        Class<? extends Annotation> markerInterface = (Class<? extends Annotation>) classLoader.loadClass(annotationClassName);
        LOG.info("find annotation name is :"+markerInterface.getSimpleName());
      }

    } catch (Exception ex) {
      readerContext.error(ex.getMessage(), readerContext.extractSource(element), ex.getCause());
    }
    String basePackage = element.getAttribute(ATTRIBUTE_BASE_PACKAGE);
    scanner.scan(StringUtils.tokenizeToStringArray(basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    return null;
  }

}
