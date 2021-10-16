package com.example.TMS.Web;

import com.example.TMS.Security.JwtAuthenticationEntryPoint;
import com.example.TMS.Security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

     @Autowired
     JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Bean
    public JwtFilter jwtFilter()
    {
       return new JwtFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
        httpSecurity
                .authorizeRequests().antMatchers("/user/create", "/user/loggedin","/role/create").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().permitAll();

        httpSecurity.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /*@Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
                .ignoring()
                .antMatchers("/user/create","/user/loggedin","/role/create");
    }*/
}
