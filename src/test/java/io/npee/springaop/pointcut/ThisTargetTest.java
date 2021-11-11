package io.npee.springaop.pointcut;

import io.npee.springaop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
// @SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB, default
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK Dynamic proxy
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    static class ThisTargetAspect {

        @Pointcut("execution(* io.npee.springaop..*.*(..))")
        private void basePackage() {}

        @Around("basePackage() && this(io.npee.springaop.member.MemberService)")
        public Object thisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this interface] {}, proxy={}", joinPoint.getSignature(), joinPoint.getThis().getClass());
            return joinPoint.proceed();
        }

        @Around("basePackage() && target(io.npee.springaop.member.MemberService)")
        public Object targetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target interface] {}, proxy={}", joinPoint.getSignature(), joinPoint.getTarget().getClass());
            return joinPoint.proceed();
        }

        @Around("basePackage() && this(io.npee.springaop.member.MemberServiceImpl)")
        public Object thisConcrete(ProceedingJoinPoint joinPoint) throws Throwable {
            // JDK 동적 프록시 모드일 경우 프록시 대상으로 지정되지 않는다.
            log.info("[this concrete] {}, proxy={}", joinPoint.getSignature(), joinPoint.getThis().getClass());
            return joinPoint.proceed();
        }

        @Around("basePackage() && target(io.npee.springaop.member.MemberServiceImpl)")
        public Object targetConcrete(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target concrete] {}, proxy={}", joinPoint.getSignature(), joinPoint.getTarget().getClass());
            return joinPoint.proceed();
        }
    }
}
