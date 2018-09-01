package test;

import com.myspring.HelloWorldService;
import com.myspring.HelloWorldServiceImpl;
import com.myspring.aop.AdvisedSupport;
import com.myspring.aop.JdkDynamicAopProxy;
import com.myspring.aop.TargetSource;
import com.myspring.aop.TimerInterceptor;
import com.myspring.context.ApplicationContext;
import com.myspring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class JDKAopTest {
    @Test
    public void testInterceptor() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();

        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, helloWorldService.getClass(), helloWorldService.getClass().getInterfaces());
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

        // 4. 基于AOP的调用
        helloWorldServiceProxy.helloWorld();

    }
}
