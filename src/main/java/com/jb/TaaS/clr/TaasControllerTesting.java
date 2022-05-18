package com.jb.TaaS.clr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(3)
@RequiredArgsConstructor
public class TaasControllerTesting implements CommandLineRunner {

    private final RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        // TODO: 18/05/2022 RestTemplate tests 
    }
}
