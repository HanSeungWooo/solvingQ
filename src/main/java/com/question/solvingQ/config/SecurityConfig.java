package com.question.solvingQ.config;

import com.question.solvingQ.exception.WebAccessDeniedHandler;
import com.question.solvingQ.login.service.PrincipalDetailsService;
import com.question.solvingQ.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final WebAccessDeniedHandler webAccessDeniedHandler;
    @Autowired
    protected PrincipalDetailsService userDetailsService;
    private final static String REMEMBER_ME_KEY = "uniqueAndSecret";

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
                    .successHandler(successHandler())
                    .failureUrl("/security-login/login?error")
                    .and()
                .rememberMe()
                    .rememberMeParameter("remember")
                    .rememberMeServices(rememberMeServices())
                .and()
                .logout()
                    .logoutUrl("/security-login/logout")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }

    @Bean
    public AuthSuccessHandler successHandler(){
        return new AuthSuccessHandler();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        services.setAlwaysRemember(false);
        services.setCookieName("remember");
        services.setParameter("remember");
        services.setTokenValiditySeconds((60 * 60 * 24) * 7);  // 유효기간을 7일로 설정
        return services;
    }
}
