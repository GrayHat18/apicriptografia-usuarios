package com.grayhat.apicriptografiausuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApicriptografiausuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApicriptografiausuariosApplication.class, args);
    }

}
