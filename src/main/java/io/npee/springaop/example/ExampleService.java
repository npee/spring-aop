package io.npee.springaop.example;

import io.npee.springaop.example.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    @Trace
    public void request(String itemId) {
        exampleRepository.save(itemId);
    }

}
