package com.microservicios.gateway.beans;

import com.microservicios.gateway.filters.AuthFilter;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.netty.http.client.HttpClient;

import java.util.Set;

// sin el * solo permite get si se lo colocamos permite el post, delete, etc
@Configuration
public class GatewayConfig {

    private final AuthFilter authFilter;

    public GatewayConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("company", route -> route.path("/companies-crud/company/*")
                        .uri("http://localhost:8081"))
                .route("report", route -> route.path("/report-ms/report/*")
                        .uri("http://localhost:7070"))
                .build();
    }

    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaON(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("company", route -> route.path("/companies-crud/company/**")
                        .uri("lb://companies-crud"))
                .route("report", route -> route.path("/report-ms/report/**")
                        .uri("lb://report-ms"))
                .build();
    }


    @Bean
    @Profile(value = "eureka-on-cb")
    public RouteLocator routeLocatorEurekaOnCn(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("company", route -> route.path("/companies-crud/company/**")
                        .filters(filter -> {
                            filter.circuitBreaker(config -> config
                                    .setName("gateway-cb")
                                    .setStatusCodes(Set.of("500", "400"))
                                    .setFallbackUri("forward:/companies-crud-fallback/company/*"));
                            return filter;
                        })
                        .uri("lb://companies-crud"))
                .route("report", route -> route.path("/report-ms/report/**")
                        .uri("lb://report-ms"))
                .route("fallback", route -> route.path("/companies-crud-fallback/company/**")
                        .uri("lb://companies-crud-fallback"))
                .build();
    }


    @Bean
    @Profile("oauth2")
    public RouteLocator routeLocatorOauth2(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("company", route -> route
                        .path("/companies-crud/company/**")
                        .filters(filter -> {

                            filter.filter(this.authFilter);

                            filter.circuitBreaker(config -> config
                                    .setName("gateway-cb")
                                    .setStatusCodes(Set.of("500"))
                                    .setFallbackUri("forward:/companies-crud-fallback/company/**"));

                            return filter;
                        })
                        .uri("lb://companies-crud"))

                .route("report", route -> route
                        .path("/report-ms/report/**")
                        .filters(filter -> filter.filter(this.authFilter))
                        .uri("lb://report-ms"))

                .route("fallback", route -> route
                        .path("/companies-crud-fallback/company/**")
                        .uri("lb://companies-crud-fallback"))

                .route("auth-server", route -> route
                        .path("/auth-server/auth/**")
                        .uri("lb://auth-server"))
                .build();
    }


    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}
