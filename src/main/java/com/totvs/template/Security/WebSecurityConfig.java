package com.totvs.template.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private CommonAuthenticationEntryPoint unauthorizedHandler;
    
    @Bean
    public CommonAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new CommonAuthenticationTokenFilter();
    }
    
	@Bean
	public CorsFilter corsFilter() {

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true); 
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
	    config.addAllowedMethod("DELETE");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable();

		httpSecurity
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
				.anyRequest().authenticated();

        httpSecurity
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Custom JWT based security filter
        httpSecurity
        		.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();

    }
	
}
