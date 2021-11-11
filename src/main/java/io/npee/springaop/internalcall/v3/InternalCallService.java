package io.npee.springaop.internalcall.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InternalCallService {

    public void internal() {
        log.info("internal call");
    }
}
