package com.showmeyourcode.spring_cloud.demo.factory.configuration;

import com.showmeyourcode.spring_cloud.demo.factory.api.OrdersEndpointSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@EnableWebFluxSecurity
@Configuration
public class SecurityConfiguration implements WebFluxConfigurer {

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        log.info("Configuring SecurityWebFilterChain...");
        var reportPath = OrdersEndpointSpecification.ENDPOINT_PATH+OrdersEndpointSpecification.REPORT_PATH;
        return http
                .httpBasic()
                .and()
                .formLogin(withDefaults())
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/favicon.ico").permitAll()
                .pathMatchers(HttpMethod.GET, reportPath).authenticated()
                .anyExchange().permitAll()
                .and()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600);
    }
}
