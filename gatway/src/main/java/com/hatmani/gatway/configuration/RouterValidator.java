package com.hatmani.gatway.configuration;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
@Component
public class RouterValidator {
    public static final List<String> openApiEndpoints = Arrays.asList(
            "/auth/signin",
            "/auth/signupadmin",
            "/auth/signupinvite",
            "/auth/ping",
            "/movies/",
            "/api/ressources/"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()

                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
