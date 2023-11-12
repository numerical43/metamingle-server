package com.mingles.metamingle.configuration;

import com.mingles.metamingle.auth.Jwt401Handler;
import com.mingles.metamingle.auth.Jwt403Handler;
import com.mingles.metamingle.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CorsFilter corsFilter;
    private final Jwt401Handler jwt401Handler;
    private final Jwt403Handler jwt403Handler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(jwt401Handler)
                .accessDeniedHandler(jwt403Handler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                // .antMatchers("/member/**").permitAll()
                // .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요

                .and()
                .apply(new JwtSecurityConfig(jwtTokenProvider));

        return http.build();
    }
}
