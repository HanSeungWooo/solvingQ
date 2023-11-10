package com.question.solvingQ.config;

import com.question.solvingQ.exception.WebAccessDeniedHandler;
import com.question.solvingQ.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final WebAccessDeniedHandler webAccessDeniedHandler;

    @Autowired
    public SecurityConfig(WebAccessDeniedHandler webAccessDeniedHandler){
        this.webAccessDeniedHandler = webAccessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/security-login/info").authenticated()
                    .antMatchers("/security-login/admin/**").hasAuthority(UserRole.ADMIN.name())
                    .anyRequest().permitAll()
                    .and()
                .exceptionHandling().accessDeniedHandler(webAccessDeniedHandler)
                    .and()
                .formLogin()
                    .usernameParameter("loginId")
                    .passwordParameter("password")
                    .loginPage("/security-login/login")
                    .defaultSuccessUrl("/security-login")
                    .failureUrl("/security-login/login")
                    .and()
                .logout()
                    .logoutUrl("/security-login/logout")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }
}
