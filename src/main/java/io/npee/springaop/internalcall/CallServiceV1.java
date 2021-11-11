package io.npee.springaop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    // 생성자 주입 순환 참조 오류
//     private final CallServiceV1 callServiceV1;

//    @Autowired
//    public CallServiceV1(CallServiceV1 callServiceV1) {
//        this.callServiceV1 = callServiceV1;
//    }
    private CallServiceV1 callServiceV1;

    // setter 주입
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("CallServiceV1 Setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("external call");
        callServiceV1.internal();
    }

    public void internal() {
        log.info("internal call");
    }
}
