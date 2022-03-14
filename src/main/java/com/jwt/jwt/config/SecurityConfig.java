package com.jwt.jwt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 기본적인 웹 보안을 활성화하겠다는 annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 추가적인 설정 방벙 1. WebSecurityConfigurer를 implements 2. WebSecurityConfigurerAdapter를 extends

    // WebSecurityConfigurerAdapter 안의 configure 메소드를 override
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/jwt").permitAll()
                .anyRequest().authenticated();

        // authorizeRequests : HttpServeletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다
        // api/jwt에 대한 요청은 인증 업시 접근 허용하겠다
        // 나머지 요청들에 대해서는 모두 인증을 받아야 한다
        // -> 이런 다음에는 unauthorized가 풀린다


    }

    // h2-console 하위 모든 요청과 파비콘은 모두 무시하는 것으로 설정
    @Override
    public void configure(WebSecurity web) throws Exception{
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                );
    }
}

