package com.myspring.context;

import com.myspring.bean.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(Object bean, BeanDefinition beanDefinition) throws Exception {
        bean = beanDefinition.getBeanClass().newInstance();
        beanDefinition.setBean(bean);
        applyProPertyValues(bean,beanDefinition);
        return bean;
    }

    private void applyProPertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if(propertyValues == null) return;
        if(propertyValues.getPropertyValueList() == null) return;
        for(PropertyValue propertyValue : propertyValues.getPropertyValueList()){
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
//                Method method = bean.getClass().getDeclaredMethod("set" + propertyValue.getName().substring(0, 1).toUpperCase()
//                        + propertyValue.getName().substring(1), value.getClass());
//                method.setAccessible(true);
//                method.invoke(bean,value);
//            }else{
//                Field field = bean.getClass().getDeclaredField(propertyValue.getName());
//                field.setAccessible(true);
//                field.set(bean,value);
//            }
        }
    }
}
