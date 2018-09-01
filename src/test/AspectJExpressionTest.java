package test;

import com.myspring.HelloWorldService;
import com.myspring.HelloWorldServiceImpl;
import com.myspring.OutputService;
import com.myspring.aop.AspectJExpressionPointcut;
import org.junit.Assert;
import org.junit.Test;

public class AspectJExpressionTest {

    @Test
    public void testMethodInterceptor() throws Exception {
        //String expression = "execution(* com.myspring.*.*(..))";
        String expression = "execution(* com.myspring..HelloWorldService.*())";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"),HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
}
