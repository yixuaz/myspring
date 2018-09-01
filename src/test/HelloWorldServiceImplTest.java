package test;

import com.myspring.HelloWorldService;
import com.myspring.HelloWorldServiceImpl;
import com.myspring.context.ApplicationContext;
import com.myspring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldServiceImplTest {

    @Test
    public void helloWorld() throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        System.out.println("class:"+helloWorldService.getClass());
        helloWorldService.helloWorld();
    }
}