package io.npee.springaop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("execution(* io.npee.springaop.order..*(..))")
    public void allOrder() {}

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
