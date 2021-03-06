package com.gmail.creativegeeksuresh.zyncky.configuration;

import javax.servlet.http.HttpServletResponse;

import com.gmail.creativegeeksuresh.zyncky.security.CustomUserDetailsService;
import com.gmail.creativegeeksuresh.zyncky.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Autowired
  CustomUserDetailsService userDetailsService;

  @Autowired
  JwtTokenFilter jwtTokenFilter;
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
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

    // http.authorizeRequests().antMatchers("/api/v1/user/**").permitAll().anyRequest().authenticated().and().csrf()
    // .disable().formLogin().loginPage("/login").permitAll().usernameParameter("userName")
    // .passwordParameter("password").successHandler(authSuccessHandler).failureUrl("/login?accessdenied").and()
    // .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().and().exceptionHandling()
    // .accessDeniedHandler(accessDeniedHandler);

    // http.headers().frameOptions().disable();
    // Enable CORS and disable CSRF
    http = http.cors().and().csrf().disable();

    // Set session management to stateless
    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

    // Set unauthorized requests exception handler
    http = http
    .exceptionHandling()
    .authenticationEntryPoint(
        (request, response, ex) -> {
            response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                ex.getMessage()
            );
        }
    )
    .and();

    // Set permissions on endpoints
    http.authorizeRequests()
    // Our public endpoints
    .antMatchers("/api/v1/user/login").permitAll()
    .antMatchers("/api/v1/user/create-account").permitAll()
    // .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
    // .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
    // .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
    // .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
    // Our private endpoints
    .anyRequest().authenticated();

    // Add JWT token filter
    http.addFilterBefore(
      jwtTokenFilter,
      UsernamePasswordAuthenticationFilter.class
  );
  }

  // private UserDetails getUserDetails()throws Exception{
  // UserDetails user = new UserDetails(){

  // };
  // }
}
