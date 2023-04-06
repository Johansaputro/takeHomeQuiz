package com.takehometest.TakeHomeTest.libraries.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@ConditionalOnClass(Retrofit.class)
public class RetrofitConfiguration {
    @Bean
    public Retrofit retrofitDansApi(DansApiConfiguration dansApiConfiguration, OkHttpClient dansApiHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        if (dansApiHttpClient != null) {
            builder.client(dansApiHttpClient);
        }

        builder.baseUrl(dansApiConfiguration.getHost());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        builder.addConverterFactory(JacksonConverterFactory.create(objectMapper));

        return builder.build();
    }

    @Bean
    public DansEndpointService dansEndpointService(Retrofit retrofitDansApi) {
        return retrofitDansApi.create(DansEndpointService.class);
    }
}
