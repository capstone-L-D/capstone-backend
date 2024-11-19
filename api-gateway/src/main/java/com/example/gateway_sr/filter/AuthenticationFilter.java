package com.example.gateway_sr.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory {
    @Autowired
    private RouteValidater validater;

    public AuthenticationFilter(){
        super(Config.class);
    }

    public static class Config{}
    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            // for the uris NOT specified in the RouteValidator do the following steps
            if (validater.isSecured.test(exchange.getRequest())) {
                // check if the exchange request header contains the Authorization header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing Authorization Header");
                }
                // take out the AUthorization header
                String authHeaderToken = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeaderToken != null && authHeaderToken.startsWith("Bearer")) {
                    // remove Bearer from front
                    authHeaderToken = authHeaderToken.substring(7);
                }
                try {
                    // now consume /api/auth/validate/token of authentication-service using
                    // RestClient
                    // can keep this call in a seperate JwtUtil class and call
                    RestClient restClient = RestClient.create();
                    restClient
                            .get()
                            .uri("http://localhost:7070/api/auth/validate/token?token=" + authHeaderToken)
                            .retrieve()
                            .body(Boolean.class);
                    // also instead of making a RestClient call for every request, we can validate
                    // the token here in api-gateway itself
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("Invalid Access!! : " + e.getMessage());
                }
            }
            // for other uris simply chain the request.
            return chain.filter(exchange);
        });
    }

}

