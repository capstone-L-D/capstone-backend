package com.example.gateway_sr.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidater {



public static final List<String> openApiEndpoints = List.of("/api/auth/register",
        "/api/auth/login",
        "/api/auth/validate/token");
public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
        .stream()
        .noneMatch(uri -> request
                .getURI()
                .getPath()
                .contains(uri));
}