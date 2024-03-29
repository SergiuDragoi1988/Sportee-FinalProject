package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
    }

    //hardcodat ... ar putea fi folosit pentru admin
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("memuser").password("{noop}pass").roles("USER").build();
//        return new InMemoryUserDetailsManager(user);
//    }


}
