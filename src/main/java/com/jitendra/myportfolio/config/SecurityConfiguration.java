package com.jitendra.myportfolio.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

         return httpSecurity.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
             CorsConfiguration configuration = new CorsConfiguration();
             String allowedOrigin = System.getenv("ALLOWED_ORIGINS");
             configuration.setAllowedOrigins(Arrays.asList(allowedOrigin.split(",")));
             return configuration;
         }))
             .authorizeHttpRequests((requests) -> requests.requestMatchers(HttpMethod.POST, "/articles")
                                                                        .authenticated()
                                                                        .requestMatchers(HttpMethod.GET, "/articles")
                                                                        .permitAll()
                                                                        .requestMatchers(HttpMethod.GET, "/articles/*")
                                                                        .permitAll())
                            .csrf(csrf -> csrf.disable())
                           .rememberMe(Customizer.withDefaults())
                           .formLogin(Customizer.withDefaults())
                           .httpBasic(Customizer.withDefaults())
                           .build();
    }
}
