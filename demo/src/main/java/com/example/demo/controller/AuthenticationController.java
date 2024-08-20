package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTOs.AuthenticationRequest;
import com.example.demo.DTOs.AuthenticationResponse;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JwtUtil;
import com.example.demo.pojo.MyUserPrincipal;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

   
    private AuthenticationManager authenticationManager;

    
    private MyUserDetailsService userDetailsService;

    
    private JwtUtil jwtUtil;
    
    AuthenticationController(AuthenticationManager authenticationManager,MyUserDetailsService userDetailsService,JwtUtil jwtUtil){
        this.authenticationManager=authenticationManager;
        this.userDetailsService=userDetailsService;
        this.jwtUtil=jwtUtil;
    }



    @PostMapping("/login")
    public <Authentication> ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        final UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // Validate role from the request body with the role in the database
        String requestRole = authenticationRequest.getRole();
        System.out.println(requestRole);
        String userRole = ((MyUserPrincipal) user).getUser().getRole();
        System.out.println(userRole);
        if (!userRole.equals(requestRole)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // change the role type to grantedAuthorities
        List<GrantedAuthority> roles=new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(requestRole);
        roles.add(simpleGrantedAuthority);

        try {
            var authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword(),roles));
            UserDetails userDetails=(UserDetails)authentication.getPrincipal();     
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

     
    }
}

