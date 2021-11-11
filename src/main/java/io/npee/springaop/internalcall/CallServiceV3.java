package io.npee.springaop.internalcall;

import io.npee.springaop.internalcall.v3.InternalCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalCallService internalCallService;

    public void external() {
        log.info("external call");
        internalCallService.internal();
    }

    public void internal() {
        log.info("internal call");
    }
}
