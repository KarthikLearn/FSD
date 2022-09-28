package com.stackroute.zuulservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if(httpRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
		{
			chain.doFilter(httpRequest, httpResponse);
			 
		}
		else {
			
			String authHeader = httpRequest.getHeader("Authorization");
			System.out.println("AuthHeader" +  authHeader);
			if(authHeader == null || !authHeader.startsWith("Bearer")) {
				throw new ServletException("Missing or Invalid Authentication Header");
			}
			
			String jwtToken = authHeader.substring(7);
			
			Claims claims = Jwts.parser().setSigningKey("userauthkey").parseClaimsJws(jwtToken).getBody();
			httpRequest.setAttribute("claims", claims);
			chain.doFilter(request, response);
		}
		
	}
	
	

}

