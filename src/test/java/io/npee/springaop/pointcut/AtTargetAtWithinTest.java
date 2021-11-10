package io.npee.springaop.pointcut;

import io.npee.springaop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(AtTargetAtWithinTest.Config.class)
@SpringBootTest
public class AtTargetAtWithinTest {

    @Autowired
    Child child;

    @Test
    void success() {
        log.info("child proxy={}", child.getClass());
        child.childMethod();
        child.parentMethod();
    }

    static class Config {
        @Bean
        public MyAspect myAspect() {
            return new MyAspect();
        }

        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean Child child() {
            return new Child();
        }
    }

    static class Parent {
        public void parentMethod() {
            log.info("부모 메서드 실행");
        }
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod() {
            log.info("자식 메서드 실행");
        }
    }

    @Aspect
    static class MyAspect {

        @Pointcut("execution(* io.npee.springaop..*(..))")
        public void basePackage() {}

        @Around("basePackage() && @target(io.npee.springaop.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("basePackage() && @within(io.npee.springaop.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

    }
}
