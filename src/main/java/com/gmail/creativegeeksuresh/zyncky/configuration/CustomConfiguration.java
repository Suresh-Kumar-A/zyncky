package com.gmail.creativegeeksuresh.zyncky.configuration;

import com.gmail.creativegeeksuresh.zyncky.security.CustomAccessDeniedHandler;
import com.gmail.creativegeeksuresh.zyncky.security.CustomAuthSuccessHandler;
import com.gmail.creativegeeksuresh.zyncky.security.CustomUserDetailsService;
import com.gmail.creativegeeksuresh.zyncky.service.UserService;
import com.gmail.creativegeeksuresh.zyncky.service.util.AppConstants;

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

@Configuration
@EnableWebSecurity
public class CustomConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Autowired
  CustomUserDetailsService userDetailsService;

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

    // http.headers().frameOptions().disable();
    // Enable CORS and disable CSRF
    http = http.cors().and().csrf().disable();

    // Set session management to stateless
    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and();

    // Set unauthorized requests exception handler
    // http = http
    // .exceptionHandling()
    // .authenticationEntryPoint(
    //     (request, response, ex) -> {
    //         response.sendError(
    //             HttpServletResponse.SC_UNAUTHORIZED,
    //             ex.getMessage()
    //         );
    //     }
    // )
    // .and();

    http.authorizeRequests()
    .antMatchers("/global/**").permitAll()
    // .antMatchers("/api/v1/user/login").permitAll()
    .antMatchers("/api/v1/global/**").permitAll()
    .antMatchers("/user/**","/api/v1/user/**").hasAnyRole(AppConstants.USER_ROLE,AppConstants.ADMIN_ROLE)
    .antMatchers("/admin/**","/api/v1/admin/**").hasRole(AppConstants.ADMIN_ROLE)
    .anyRequest().authenticated()
    .and()
    .formLogin().loginPage("/global/login").permitAll().usernameParameter("username")
    .passwordParameter("password")
    .successHandler(authSuccessHandler).failureUrl("/login?accessdenied").and()
    .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll()
    .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    // Set permissions on endpoints
    // http.authorizeRequests()
    // // Our public endpoints
    // .antMatchers("/global/**").permitAll()
    // .antMatchers("/api/v1/user/login").permitAll()
    // .antMatchers("/api/v1/user/create-account").permitAll()
    // // .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
    // // .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
    // // .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
    // // .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
    // // Our private endpoints
    // .anyRequest().authenticated();

    // Add JWT token filter
  //   http.addFilterBefore(
  //     jwtTokenFilter,
  //     UsernamePasswordAuthenticationFilter.class
  // );
  }

}
