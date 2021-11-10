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
import org.springframework.stereotype.Component;

@Slf4j
@Import(AtAnnotationTest.AtAnnotationAspect.class)
@SpringBootTest
public class AtAnnotationTest {

    @Autowired
    MemberService memberService;

    /**
     * [@annotation]
     * 메서드에 해당 애너테이션이 붙어있는 경우에만 AOP 적용
     *
     * [@args]
     * 파라미터에 @Check 애너테이션이 붙어있는 메서드들을 찾아서 AOP 적용
     */
    @Test
    void success() {
        log.info("memberService proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    static class AtAnnotationAspect {

        @Pointcut("execution(* io.npee.springaop..*(..))")
        public void basePackage() {}

        @Around("basePackage() && @annotation(io.npee.springaop.member.annotation.MethodAop)")
        public Object atAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

}
