package io.npee.springaop.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

    @Before("@annotation(io.npee.springaop.example.annotation.Trace)")
    public void doTrace(JoinPoint joinPoint) {
        log.info("[trace] {}, args={}", joinPoint.getSignature(), joinPoint.getArgs());
    }
}
