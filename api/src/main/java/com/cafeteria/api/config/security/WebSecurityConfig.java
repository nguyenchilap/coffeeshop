package com.cafeteria.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService);
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/api/v1/login/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/v1/store/**").hasAnyAuthority("Admin")
			.antMatchers(HttpMethod.DELETE, "/api/v1/store/**").hasAnyAuthority("Admin")
			.antMatchers(HttpMethod.PUT, "/api/v1/store/**").hasAnyAuthority("Admin")
			
			.antMatchers(HttpMethod.POST, "/api/v1/employee/**").hasAnyAuthority("Admin")
			.antMatchers(HttpMethod.DELETE, "/api/v1/employee/**").hasAnyAuthority("Admin")
			
			.antMatchers(HttpMethod.POST, "/api/v1/voucher/**").hasAnyAuthority("Admin")
			.antMatchers(HttpMethod.DELETE, "/api/v1/voucher/**").hasAnyAuthority("Admin")
			.antMatchers(HttpMethod.PUT, "/api/v1/voucher/**").hasAnyAuthority("Admin")
			
			.antMatchers(HttpMethod.POST, "/api/v1/category/**").hasAnyAuthority("Admin")
			.antMatchers(HttpMethod.DELETE, "/api/v1/category/**").hasAnyAuthority("Admin")
			
			.antMatchers(HttpMethod.POST, "/api/v1/beverage/**").hasAnyAuthority("Admin")
			.antMatchers(HttpMethod.DELETE, "/api/v1/beverage/**").hasAnyAuthority("Admin")
			.anyRequest().fullyAuthenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
