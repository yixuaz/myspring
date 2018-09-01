package com.myspring.context;

public interface BeanFactory {
    Object getBean(String beanName) throws Exception;
}
