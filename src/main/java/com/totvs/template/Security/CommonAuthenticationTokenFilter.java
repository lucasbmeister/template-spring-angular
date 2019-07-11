package com.totvs.template.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.totvs.template.Annotations.ExcludeAnnotationStrategy;
import com.totvs.template.Domain.Entities.Security.Role;
import com.totvs.template.Domain.Entities.Security.User;
import com.totvs.template.Services.Security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonAuthenticationTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private SecurityService securityService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    	
    	String token = request.getHeader("Authorization");

        if ( token != null && !token.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
        	token = token.replace("Bearer ", "");
        	try{
        		
        		String jsonUserDetails = securityService.verifyTokenJson(token);
                UserDetails userDetails = prepareUserDetails(jsonUserDetails);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                
        	}catch(Exception e){
        		logger.error(e.getMessage());
        	}
        	
        }

        chain.doFilter(request, response);
    }
    
    private UserDetails prepareUserDetails(String jsonUserDetails) throws JsonProcessingException, IOException{
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode root = objectMapper.readTree(jsonUserDetails);
    	
    	long userId = root.get("id").asLong();
    	String username = root.get("username").asText();

		Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeAnnotationStrategy()).create();

    	List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_PRIVATE_USER"));

    	if(root.get("roles") != null) {
        	for(JsonNode role : root.get("roles")) {

				Role roleEntity = gson.fromJson(role.toString(), Role.class);

        		authorities.add(new SimpleGrantedAuthority("ROLE_" + roleEntity.getRole()));
        	}
    	}
    	
    	return new AuthUser(userId, username, authorities);
    }
}

