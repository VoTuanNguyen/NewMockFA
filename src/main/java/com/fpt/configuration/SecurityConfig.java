package com.fpt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.fpt.security.CustomAccessDeniedHandler;
import com.fpt.security.JwtAuthenticationTokenFilter;
import com.fpt.security.RestAuthenticationEntryPoint;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebSecurity
@EnableSwagger2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
		jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
		return jwtAuthenticationTokenFilter;
	}

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	// configuration swagger 2
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	protected void configure(HttpSecurity http) throws Exception {
		// Disable crsf for url /**
		http.csrf().ignoringAntMatchers("/**").disable();

		http.authorizeRequests().antMatchers("/trip/**").permitAll();
		http.authorizeRequests().antMatchers("/route/**").permitAll();
		http.authorizeRequests().antMatchers("/booking/**").permitAll();
		http.authorizeRequests().antMatchers("/seat/**").permitAll();
		http.authorizeRequests().antMatchers("/account/**").permitAll();
		http.authorizeRequests().antMatchers("/payment/**").permitAll();
		http.authorizeRequests().antMatchers("/jsa/**").permitAll();

		http.antMatcher("/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				// .antMatchers(HttpMethod.GET, "/rest/**").access("hasRole('ROLE_ADMIN') or
				// hasRole('ROLE_USER')").and()
				.antMatchers(HttpMethod.GET, "/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers(HttpMethod.POST, "/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers(HttpMethod.PUT, "/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers(HttpMethod.GET, "/staff/**").access("hasRole('ROLE_STAFF')")
				.antMatchers(HttpMethod.POST, "/staff/**").access("hasRole('ROLE_STAFF')").and()
				.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
	}
}
