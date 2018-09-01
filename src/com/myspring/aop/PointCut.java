package com.myspring.aop;



public interface PointCut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
