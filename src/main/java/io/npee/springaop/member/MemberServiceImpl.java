package io.npee.springaop.member;

import io.npee.springaop.member.annotation.ClassAop;
import io.npee.springaop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@Component
@ClassAop
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
