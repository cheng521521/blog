package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @ClassName WebConfig
 * @Author jackchen
 * @Date 2022/8/23 17:10
 * @Description 配置安全策略
 **/
@Configuration
public class WebConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //省略HttpSecurity的配置
        httpSecurity.authorizeRequests().antMatchers("/user/login").permitAll().anyRequest().authenticated()
                .and().formLogin().failureForwardUrl("/user/login")
                .and().csrf().disable();
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/console/**");
    }
}
