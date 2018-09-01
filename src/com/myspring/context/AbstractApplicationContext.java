package com.myspring.context;

import com.myspring.bean.BeanPostProcessor;

import java.util.List;

public abstract class AbstractApplicationContext implements  ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    
    public void refresh() throws  Exception {
        loadBeanDefinitions();
        registerBeanPostProcessors(beanFactory);
        finishBeanFactoryInitialization();
    }

    private void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected void finishBeanFactoryInitialization() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void loadBeanDefinitions() throws Exception;

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }
}
