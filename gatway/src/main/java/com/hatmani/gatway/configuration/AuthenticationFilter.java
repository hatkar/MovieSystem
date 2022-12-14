package com.hatmani.gatway.configuration;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//Implementation de spring cloud custom gateway filters
@RefreshScope
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {




    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RouterValidator routerValidator;
    public AuthenticationFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            System.out.println("requete demander est :"+request.getURI().getPath());
            // JWTUtil can extract the token from the request, parse it and verify if the given role is available
            if (routerValidator.isSecured.test(request)) {
                if (this.isAuthMissing(request))
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You cannot consume this service. Please check your api key.");        //DataBuffer buffer = response.bufferFactory().wrap(bytes);

//                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

                final String token = this.getAuthHeader(request);

                if (jwtUtil.isInvalid(token))
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You cannot consume this service. Please check your api key.");        //DataBuffer buffer = response.bufferFactory().wrap(bytes);

                   // return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
                if(!jwtUtil.hasRole(token, config.getRole())){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot consume this service. Please check your api key.");        //DataBuffer buffer = response.bufferFactory().wrap(bytes);

                    //return this.onError(exchange, "UNAUTHORIZED Role", HttpStatus.UNAUTHORIZED);
                }
                this.populateRequestWithHeaders(exchange, token);
            }


            return chain.filter(exchange);
        };
    }




    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
         //response.set.writeWith(err);
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }


@AllArgsConstructor
@NoArgsConstructor
@Data
    public static class Config {
        private String role;
    }

}
