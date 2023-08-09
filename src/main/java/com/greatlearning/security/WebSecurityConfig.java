package com.greatlearning.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.service.UserDetailsServiceImpl;



@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuth());
	}

	@Bean
	public  DaoAuthenticationProvider myAuth() {
		DaoAuthenticationProvider  authProvider = new DaoAuthenticationProvider ();
		authProvider.setUserDetailsService(myUser());
		authProvider.setPasswordEncoder(myPas());
		return authProvider;
	}

	@Bean
public  BCryptPasswordEncoder myPas() {
	return new  BCryptPasswordEncoder();
}

	@Bean
	public  UserDetailsService myUser() {
		return new UserDetailsServiceImpl();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/","/students/save","/students/showFormForAdd","/students/403").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/students/showFormForUpdate","/students/delete").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/students/list").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/students/403")
        .and()
        .cors().and().csrf().disable();

	}
	

	
}
