package com.backbase.atmlocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class AtmlocatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(AtmlocatorApplication.class, args);
  }
  
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
  }
}
