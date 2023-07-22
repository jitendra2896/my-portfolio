package com.jitendra.myportfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers(HttpMethod.POST, "/articles")
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
