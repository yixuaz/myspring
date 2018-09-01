package com.myspring.aop;

public interface PointcutAdvisor extends Advisor{
    PointCut getPointCut();
}
