package com.roman.awsintegration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AwsIntegrationApplication.class)
public class AwsIntegrationApplicationTests {

    @Test
    void contextLoads() {
    }

}