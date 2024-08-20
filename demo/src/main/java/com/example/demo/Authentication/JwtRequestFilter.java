package com.example.demo.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.List;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
                System.out.println(username);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // UserDetails userDetails =(UserDetails) this.userDetailsService.loadUserByUsername(username);
            List<String> roles=jwtUtil.getRolesFromToken(jwt).stream().toList();
            String roleFromToken=roles.get(0);
            System.out.println("roleFromToken"+roleFromToken);
            UserDetails userDetails= User.withUsername(username).password("").authorities(roleFromToken).build();
            Boolean validateToken=jwtUtil.validateToken(jwt, userDetails);
            System.out.println("Ismytoken valid"+validateToken); 

            if (validateToken) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                System.out.println(usernamePasswordAuthenticationToken.getAuthorities());
            
                    usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

//         response.setHeader("Access-Control-Allow-Origin", "*"); // * = all domainName
//         response.setHeader("Access-Control-Allow-Credentials", "true"); // allow CrossDomain to use Origin Domain
//         response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT"); 
//         response.setHeader("Access-Control-Max-Age", "3600"); // Preflight cache duration in browser
// //        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me"); 
//         response.setHeader("Access-Control-Allow-Headers", "*"); // all header

//         // chain.doFilter(req, res);
        chain.doFilter(request, response);
    }
}




