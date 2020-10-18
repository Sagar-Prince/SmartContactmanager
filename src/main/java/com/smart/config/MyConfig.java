package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService getUserDetailService() {

		return new UserDetailsServiceImp();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncode() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider daoauthenticationProvider = new DaoAuthenticationProvider();
		daoauthenticationProvider.setUserDetailsService(this.getUserDetailService());
		daoauthenticationProvider.setPasswordEncoder(passwordEncode());

		return daoauthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
 
	}

}
