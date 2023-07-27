package com.jitendra.myportfolio.config;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfiguration {

    Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        logger.info(System.getenv("ALLOWED_ORIGINS"));
         return httpSecurity.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
             logger.info("**********************************");
             CorsConfiguration configuration = new CorsConfiguration();
             String allowedOrigin = System.getenv("ALLOWED_ORIGINS");
             configuration.setAllowedOrigins(Arrays.asList(allowedOrigin.split(",")));
             configuration.setAllowedMethods(Collections.singletonList("*"));
             configuration.setAllowCredentials(true);
             configuration.setAllowedHeaders(Collections.singletonList("*"));
             configuration.setMaxAge(3600L);
             return configuration;
         })).csrf(csrf -> csrf.disable())
             .authorizeHttpRequests((requests) -> requests.requestMatchers(HttpMethod.POST, "/articles")
                                                                        .authenticated()
                 .requestMatchers(HttpMethod.POST, "/contact-me")
                 .permitAll()
                                                                        .requestMatchers(HttpMethod.GET, "/articles")
                                                                        .permitAll()
                                                                        .requestMatchers(HttpMethod.GET, "/articles/*")
                                                                        .permitAll())
                           .rememberMe(Customizer.withDefaults())
                           .formLogin(Customizer.withDefaults())
                           .httpBasic(Customizer.withDefaults())
                           .build();
    }
}
