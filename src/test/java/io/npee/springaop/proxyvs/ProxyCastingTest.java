package io.npee.springaop.proxyvs;

import io.npee.springaop.member.MemberService;
import io.npee.springaop.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // jdk dynamic proxy 사용
        MemberService proxy = (MemberService) proxyFactory.getProxy();

        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl memberServiceProxyImpl = (MemberServiceImpl) proxy;
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 사용
        MemberService proxy = (MemberService) proxyFactory.getProxy();

        MemberServiceImpl memberServiceProxyImpl = (MemberServiceImpl) proxy;

    }

}
