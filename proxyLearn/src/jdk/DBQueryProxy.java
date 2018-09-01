package jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DBQueryProxy implements InvocationHandler {
    private Object tar;//要代理的对象
    public Object bind(Object obj){
        this.tar = obj;
        //取得代理对象
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(),tar.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("query Id:"+args[0]);//增强代码
        Object result = method.invoke(tar,args);
        return result;
    }
}
