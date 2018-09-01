package com.myspring.aop;

public class ProxyFactory extends AdvisedSupport implements AopProxy {
    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    protected final AopProxy createAopProxy() {
        //return new Cglib2AopProxy(this);
        if(getTargetSource().getInterfaces() == null || getTargetSource().getInterfaces().length == 0)
            return new Cglib2AopProxy(this);
        return new JdkDynamicAopProxy(this);
    }
}
