package com.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/member/**").permitAll()
	            .requestMatchers("/board/list**", "/board/detail/**").permitAll()
	            .requestMatchers("/board/write", "/board/edit/**", "/board/delete/**").permitAll() // 세션 기반 처리
	            .anyRequest().permitAll()
	        )
	        .formLogin(form -> form.disable())
	        .csrf(csrf -> csrf.disable()) // POST 시 403 방지
	        .logout(logout -> logout
	            .logoutUrl("/member/logout")
	            .logoutSuccessUrl("/board/list")
	        );

	    return http.build();
	}


}
