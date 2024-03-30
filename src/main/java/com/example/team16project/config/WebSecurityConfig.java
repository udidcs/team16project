package com.example.team16project.config;

import com.example.team16project.service.user.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public WebSecurityCustomizer configure() { // 1) 스프링 시큐리티 기능 비활성화
        return web -> web.ignoring()
                .requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(a -> a.requestMatchers(

                "/login", "/signup", "/user", "/**").permitAll().anyRequest().authenticated())
            .formLogin(a -> a.loginPage("/login")
                    .defaultSuccessUrl("/article/articles"))
                .logout(a -> a.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true))
                .csrf(a -> a.disable())
                .build();

    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}