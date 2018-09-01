package com.myspring.aop;

import org.aopalliance.intercept.MethodInterceptor;

public class AdvisedSupport {
    MethodInterceptor methodInterceptor;
    TargetSource targetSource;
    MethodMatcher methodMatcher;

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }
}
