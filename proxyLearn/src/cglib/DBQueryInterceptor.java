package cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DBQueryInterceptor implements MethodInterceptor {
    Object tar;
    public DBQueryInterceptor(Object tar){
        this.tar = tar;
    }
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//        if(!method.getName().equals("request2")){
//            System.out.println("should not see:"+args[0]);
//            return method.invoke(tar,args);
//        }
        System.out.println("query id:"+args[0]);
        Object result = method.invoke(tar,args);
        return result;
    }
}
