package com.myspring.aop;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }



    @Override
    public PointCut getPointCut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
