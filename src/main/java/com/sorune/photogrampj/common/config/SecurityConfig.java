package com.sorune.photogrampj.common.config;

import com.sorune.photogrampj.common.filter.JwtCheckFilter;
import com.sorune.photogrampj.common.handler.APILoginFailHandler;
import com.sorune.photogrampj.common.handler.APILoginSuccessHandler;
import com.sorune.photogrampj.common.handler.APILogoutSuccessHandler;
import com.sorune.photogrampj.common.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.CompositeAccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@Log4j2
@RequiredArgsConstructor
public class SecurityConfig {

    private final APILoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(configurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig ->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/css/**","/js/**","/img/**","/portfolio/**"/*이미지 소스 경로*/).permitAll()
                                .requestMatchers("/","/**").permitAll()
                                .requestMatchers("/api/login","/register").permitAll()
                                .requestMatchers("/api/upload","/api/imageUpload").permitAll()
                                .requestMatchers("/api/post/**").permitAll()
                                /*테스트용 requestMatchers*/
                                .requestMatchers("/chat").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(config->
                        config
                                .loginPage("/api/login")
                                .successHandler(loginSuccessHandler)
                                .failureHandler(new APILoginFailHandler())
                                .permitAll()
                )
                .addFilterBefore(new JwtCheckFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(config ->
                        config.logoutUrl("/api/logout")
                                .logoutSuccessHandler(new APILogoutSuccessHandler())
                                .permitAll()
                )
                .exceptionHandling(config->
                        config.accessDeniedHandler(new CompositeAccessDeniedHandler())
                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                )
        ;

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD","GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type"));
        configuration.setAllowCredentials(true);

        //setAllowedOrigins : 교차 출처 요청이 허용되는 출처 목록입니다.
        //setAllowedMethods : 허용할 HTTP 메소드 설정
        //setAllowedHeaders : 실제 요청 중에 사용이 허용되도록 사전 요청이 나열할 수 있는 헤더 목록을 설정.
        //setAllowedCredentials : 사용자 자격 증명이 지원되는지 여부

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

}
