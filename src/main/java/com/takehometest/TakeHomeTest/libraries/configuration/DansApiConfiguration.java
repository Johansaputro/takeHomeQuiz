package com.takehometest.TakeHomeTest.libraries.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("dans.api")
@Getter
@Setter
public class DansApiConfiguration {
    private String host;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;
}
