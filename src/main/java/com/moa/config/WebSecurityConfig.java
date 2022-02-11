package com.moa.config;

import com.moa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;

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
        http.csrf().disable()//csrf 토큰 비활성화
                .authorizeRequests()
                .antMatchers("/", "/login", "/signup", "/user", "/moa").permitAll() // 누구나 접근 허용
                .and()
                .formLogin()
                .usernameParameter("loginId") //로그인 아이디 속성값 변경(기본값은 username)
                .passwordParameter("pw")//로그인 비밀번호 속성값 변경(기본값은 password)
//                    .loginPage("/moa/login") //로그인 페이지 링크
                .defaultSuccessUrl("/moa") //로그인 성공 후 리다이렉트 주소
                .and()
                .logout()
                .logoutSuccessUrl("/moa/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }


////     시큐리티가 대신 로그인 해줄때 pw를 가로채는데,
////     해당 pw가 뭘로 해쉬화해서 회원가입이 되었는지 알아야 같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교가능
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // 유저서비스에서 유저디테일 서비스를 임플리먼트 해서 loadUserByUsername() 구현하기
                .passwordEncoder(new BCryptPasswordEncoder());;
    }
}

