package com.myspring.bean;

import com.myspring.context.BeanFactory;

public interface BeanFactoryAware {
    public void setBeanFactory(BeanFactory beanFactory);
}
