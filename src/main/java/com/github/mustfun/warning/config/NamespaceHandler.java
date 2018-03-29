/**
 *    Copyright 2010-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.mustfun.warning.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 基于Spring配置来实现的
 *
 * @author itar
 *
 * @see WarningBeanDefinitionParser
 * @since 1.2.0
 */
public class NamespaceHandler extends NamespaceHandlerSupport {

  /**
   * {@inheritDoc}
   */
  @Override
  public void init() {
    registerBeanDefinitionParser("scan", new WarningBeanDefinitionParser());
  }

  /**
   * example 大概是这种感觉
   *
   *
   <beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns:reg="http://www.dangdang.com/schema/ddframe/reg"
   xmlns:job="http://www.dangdang.com/schema/ddframe/job"
   xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd
   http://www.dangdang.com/schema/ddframe/reg
   http://www.dangdang.com/schema/ddframe/reg/reg.xsd
   http://www.dangdang.com/schema/ddframe/job
   http://www.dangdang.com/schema/ddframe/job/job.xsd
   ">
   <warning-control-spring-1.2:scan id="regCenter" server-lists="yourhost:2181" namespace="dd-job" base-sleep-time-milliseconds="1000" max-sleep-time-milliseconds="3000" max-retries="3" />

   </beans>
   */

}
