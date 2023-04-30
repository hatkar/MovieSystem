package com.hatmani.gatway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/movies/api/**")
                        .filters(f ->
                                f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
                                        .filter(filter.apply(
                                                new AuthenticationFilter.Config("")))
                        )
                        .uri("lb://video-service"))
                .route("ressources-service", r -> r.path("/api/ressources/**")
                                .filters(f ->
                                        f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
                                                .filter(filter.apply(
                                                        new AuthenticationFilter.Config("")))
                                )
                        .uri("lb://video-service"))
                .route("admin-service", r -> r.path("/admin/api/**")
                        .filters(f ->
                                f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
                                        .filter(filter.apply(
                                                new AuthenticationFilter.Config("ADMIN")))
                        )
                        .uri("lb://video-service"))

                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f ->
                                f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
                                        .filter(filter.apply(
                                                new AuthenticationFilter.Config("")))
                        )
                        .uri("lb://security-service"))

                .build();
    }
}
