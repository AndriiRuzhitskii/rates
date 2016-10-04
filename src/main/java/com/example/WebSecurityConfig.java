package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll();
    	http.httpBasic().and().authorizeRequests().//
		antMatchers(HttpMethod.GET, "/rate").hasRole("USER").
		antMatchers(HttpMethod.GET, "/ratebyname").hasRole("USER").
		antMatchers(HttpMethod.GET, "/ratebydate").hasRole("USER").
		antMatchers(HttpMethod.GET, "/rateaverage").hasRole("USER").
		antMatchers(HttpMethod.GET, "/ratemin").hasRole("USER").
		antMatchers(HttpMethod.GET, "/ratemax").hasRole("USER").
		and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user1").password("password").roles("USER");
    }
}