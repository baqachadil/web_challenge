package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

//Checking if the token is valid before eache request to our server
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//getting the jwt token
		String jwt = request.getHeader(SecurityConstants.HEADER);
		System.out.println(jwt);
		if(jwt==null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response); return;
		}
				
		Claims claims = Jwts.parser()
						.setSigningKey(SecurityConstants.SECRET)
						.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
						.getBody();
		String username = claims.getSubject();
		
		//getting the user's roles
		ArrayList<Map<String, String>> roles =  (ArrayList<Map<String, String>>) claims.get("roles");
		Collection<GrantedAuthority> authoroties = new ArrayList<GrantedAuthority>();
		roles.forEach(r-> 
				{authoroties.add(new SimpleGrantedAuthority(r.get("authority")));
			});
		
		//Charge the authenticated user to the context of spring security
		UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null, authoroties);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		filterChain.doFilter(request, response);
	}

}
