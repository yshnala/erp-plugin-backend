package com.goldtek.erp_plugin.security;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
        
	  response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getOutputStream().println("{"
    		  + "\"timestamp\": \"" + LocalDateTime.now()+ "\" "  
    		  + "\n" + "\"status\": \"" + HttpServletResponse.SC_UNAUTHORIZED+ "\" "      		  
    		  + "\n" + "\"error\": \"" + authException.getMessage()+ "\" "  
    		  + "\n" + "\"path\": \""+ "\" "        		  
    		  + "\n" + " }");
	}

}
