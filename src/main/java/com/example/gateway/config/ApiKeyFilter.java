package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyFilter extends AbstractGatewayFilterFactory<Object> {

    private static final String API_KEY_HEADER_NAME = "apikey";

    private final ApiKeyProperties apiKeyProperties;

    @Autowired
    public ApiKeyFilter(ApiKeyProperties apiKeyProperties) {
        super(Object.class);
        this.apiKeyProperties = apiKeyProperties;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String requestApiKey = exchange.getRequest()
                    .getHeaders()
                    .getFirst(API_KEY_HEADER_NAME);

            if (requestApiKey != null && apiKeyProperties.getValidKeys().contains(requestApiKey)) {
                return chain.filter(exchange);
            }

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        };
    }
}
