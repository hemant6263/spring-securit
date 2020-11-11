package com.assigment.security;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.assigment.security.filters.JwtRequestFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	//this code block can be used for in memory authentication and authorization
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//	
//	    //User Role
//	    UserDetails theUser = User.withUsername("hemant")
//	                    .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
//	                    .password("123").roles("USER").authorities("ROLE_USER").build();
//	    
//	    //Manager Role 
//	    UserDetails theManager = User.withUsername("doe")
//	            .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
//	            .password("321").roles("MANAGER").authorities("ROLE_ADMIN").build();
//	    
//	
//	    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//	          
//	    userDetailsManager.createUser(theUser);
//	    userDetailsManager.createUser(theManager);
//	    
//	    return userDetailsManager;
//	}
    @Bean
    ServletRegistrationBean<WebServlet> h2servletRegistration(){
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/authenticate").permitAll()
		.antMatchers("/profile/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
		.antMatchers("/actuator/**").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/console/**").hasAnyAuthority("ROLE_ADMIN")
		.and().exceptionHandling()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable();
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}