package io.npee.springaop.example;

import io.npee.springaop.example.annotation.Retry;
import io.npee.springaop.example.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleRepository {

    private static int seq = 0;

    @Trace
    @Retry(4)
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
