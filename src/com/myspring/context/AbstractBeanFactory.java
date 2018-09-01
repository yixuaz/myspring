package com.myspring.context;

import com.myspring.bean.BeanDefinition;
import com.myspring.bean.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null)
            throw new IllegalArgumentException("No bean named " + beanName + " is defined");

        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreateBean(bean,beanDefinition);
            bean = initializeBean(bean, beanName);
            beanDefinition.setBean(bean);
        }


        return bean;
    }

    private Object initializeBean(Object bean, String beanName) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }

        // TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected abstract  Object doCreateBean(Object bean, BeanDefinition beanDefinition) throws Exception;

    public void preInstantiateSingletons() throws Exception {
        for(String name : beanDefinitionMap.keySet())
            getBean(name);
    }

    public void registerBeanDefinition(String key, BeanDefinition value) {
        beanDefinitionMap.put(key, value);
    }


    public List getBeansForType(Class<?> type) throws Exception {
        List beans = new ArrayList<Object>();
        for (String beanDefinitionName : beanDefinitionMap.keySet()) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }
}
