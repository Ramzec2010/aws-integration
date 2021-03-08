package com.roman.awsintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AwsIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsIntegrationApplication.class, args);
    }

}
