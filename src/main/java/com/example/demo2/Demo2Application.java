package com.example.demo2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SpringBootApplication
@Slf4j
@RestController
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/login-govbr")
    public ResponseEntity<?> ok(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            log.info(" {} : {}", header, request.getHeader(header));
        }

        String requestURI = request.getRequestURI();

        log.info("{}", requestURI);

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/")
    public ResponseEntity<?> home(HttpServletRequest request) {

        Enumeration<?> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String header = (String) headerNames.nextElement();
            log.info(" {} : {}", header, request.getHeader(header));
        }

        String requestURI = request.getRequestURI();

        log.info("{}", requestURI);

        return ResponseEntity.ok("Bem-vindo!!!!");
    }

}
