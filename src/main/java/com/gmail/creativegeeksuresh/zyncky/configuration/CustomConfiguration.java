package com.gmail.creativegeeksuresh.zyncky.configuration;

import com.gmail.creativegeeksuresh.zyncky.constants.AppRole;
import com.gmail.creativegeeksuresh.zyncky.service.security.CustomAccessDeniedHandler;
import com.gmail.creativegeeksuresh.zyncky.service.security.CustomAuthSuccessHandler;
import com.gmail.creativegeeksuresh.zyncky.service.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  CustomUserDetailsService customUserDetailService;

  @Autowired
  CustomAccessDeniedHandler accessDeniedHandler;

  @Autowired
  CustomAuthSuccessHandler authSuccessHandler;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(customUserDetailService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/static/**", "/templates/**", "/css/**", "/js/**", "/images/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/global/**", "/", "/error/**","/api/v1/global/**").permitAll()
        .antMatchers("/admin/**", "/api/v1/admin/**", "/api/v1/app/**").hasRole(AppRole.ADMIN.name())
        .antMatchers("/user/**", "/api/v1/user/**").hasRole(AppRole.USER.name())
        .antMatchers("/mfa/**", "/", "/api/v1/mfa/**").permitAll()
        .anyRequest().authenticated().and().csrf().disable()
        .formLogin().loginPage("/global/login").usernameParameter("username").passwordParameter("password")
        .successHandler(authSuccessHandler).failureUrl("/global/login?access-denied").and()
        .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

  }
}
