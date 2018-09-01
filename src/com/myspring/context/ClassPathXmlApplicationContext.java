package com.myspring.context;

import com.myspring.bean.BeanDefinition;
import com.myspring.bean.XmlBeanDefinitionReader;
import com.myspring.io.ResourceLoader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(new AutowireCapableBeanFactory(),configLocation);
    }

    public ClassPathXmlApplicationContext(AbstractBeanFactory beanFactory, String configLocation) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition(configLocation);
        for(Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getBeanRegistry().entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }
    }

}
