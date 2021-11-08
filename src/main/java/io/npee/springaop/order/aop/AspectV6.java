package io.npee.springaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6 {

    // @Around("io.npee.springaop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            // @Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("io.npee.springaop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[@Before\t\t] [트랜잭션 시작] {}", joinPoint.getSignature());
        // 파라미터 생략 가능
        // 로직 자동 실행
    }

    @AfterReturning(value = "io.npee.springaop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[@AfterReturning] [트랜잭션 커밋] {}, return={}", joinPoint.getSignature(), result);
        // 리턴 타입에 결과가 들어갈 수 없으면 호출되지 않음
    }

    @AfterThrowing(value = "io.npee.springaop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrow(JoinPoint joinPoint, Exception ex) {
        log.info("[@AfterReturning] [트랜잭션 롤백] {}, ex={}", joinPoint.getSignature(), ex.getMessage());
        // 예외 타입에 예외가 들어갈 수 없으면 호출되지 않음
    }

    @After("io.npee.springaop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[@After\t\t\t] [리소스 릴리즈] {}", joinPoint.getSignature());
    }

}
