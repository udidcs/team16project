package com.example.team16project.config;

import com.example.team16project.service.user.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public WebSecurityCustomizer configure() { // 1) 스프링 시큐리티 기능 비활성화
        return web ->
                web
                .ignoring()
                .requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                        .requestMatchers("/login", "/signup","/user").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated())
            .formLogin(auth -> auth.loginPage("/login")
                    .defaultSuccessUrl("/article/articles"))
                .logout(auth -> auth.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true))
                .csrf(auth -> auth.disable())
                .build();
    }
    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}