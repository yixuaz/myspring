package com.myspring.bean;

import com.myspring.io.Resource;
import com.myspring.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private Map<String,BeanDefinition> beanRegistry;
    private ResourceLoader resourceLoader;
    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        beanRegistry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getBeanRegistry() {
        return beanRegistry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
