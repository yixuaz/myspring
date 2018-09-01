package aop;

import cglib.DBQuery;
import cglib.DBQueryInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AopBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().equals(DBQuery.class)) {
            return createProxy(bean);
        }
        return bean;
    }

    private <T> T createProxy(Object obj) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new DBQueryInterceptor(obj));
        return (T) enhancer.create();
    }
}
