package io.npee.springaop.proxyvs;

import io.npee.springaop.member.MemberService;
import io.npee.springaop.member.MemberServiceImpl;
import io.npee.springaop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDIAspect.class)
// @SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프곡시는 구체 클래스 캐스팅 불가능
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {

        log.info("memberService {}", memberService.getClass());
        log.info("memberServiceImpl {}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");

    }
}
