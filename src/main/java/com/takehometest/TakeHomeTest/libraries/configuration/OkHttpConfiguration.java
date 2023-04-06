package com.takehometest.TakeHomeTest.libraries.configuration;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConditionalOnClass(OkHttpClient.class)
public class OkHttpConfiguration {

    @Autowired
    private DansApiConfiguration dansApiConfiguration;

    @Bean(name = "dansApiHttpClient")
    public OkHttpClient dansApiHttpClient() {
        return getOkHttpClientBuilder(dansApiConfiguration).build();
    }


    private OkHttpClient.Builder getOkHttpClientBuilder(DansApiConfiguration dansApiConfiguration) {

        return new OkHttpClient.Builder()
                .connectTimeout(dansApiConfiguration.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(dansApiConfiguration.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(dansApiConfiguration.getWriteTimeout(), TimeUnit.MILLISECONDS);
    }
}