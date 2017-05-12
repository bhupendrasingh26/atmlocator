package com.backbase.atmlocator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * Configuration class for enabling web security
 *
 */

@Configuration
@EnableWebSecurity
public class AtmLocatorWebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${defaultUser}")
  private String defaultUserName;

  @Value("${defaultPassword}")
  private String defaultPassword;

  @Value("${role}")
  private String role;

  @Value("${contextURI}")
  private String context;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser(defaultUserName).password(defaultPassword).roles(role);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers(context).permitAll().anyRequest().authenticated().and().httpBasic();
  }

}
