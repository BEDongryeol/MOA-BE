package com.moa.config;

import com.moa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                 .antMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
                .and()
                 .formLogin()
                    .loginPage("/moa/login") //로그인 페이지 링크
                    .defaultSuccessUrl("/") //로그인 성공 후 리다이렉트 주소
                .and()
                 .logout()
                    .logoutSuccessUrl("/moa/login") // 로그아웃 성공시 리다이렉트 주소
                    .invalidateHttpSession(true) // 세션 날리기
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // 유저서비스에서 유저디테일 서비스를 임플리먼트 해서 loadUserByUsername() 구현하기
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
