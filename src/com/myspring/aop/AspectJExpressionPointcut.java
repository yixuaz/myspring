package com.myspring.aop;


import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.tools.*;

public class AspectJExpressionPointcut implements PointCut,ClassFilter,MethodMatcher {

    private String expression;
    private PointcutParser pointCutParser;
    private PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut() {
        pointCutParser = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean matches(Class targetClass) {
        checkReady();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    private void checkReady() {
        if(pointcutExpression == null)
            pointcutExpression = pointCutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReady();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        }
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
