package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.pojo.MyUserPrincipal;
import com.example.demo.pojo.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JwtUtil;



@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepo;
    

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

       
       String username=authenticationRequest.getUsername();
       User user=userRepo.findByUsername(username);
       String role=user.getRole();
      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword(),authorities)
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        // Validate role from the request body
        String requestRole = authenticationRequest.getRole();
        String userRole = ((MyUserPrincipal) userDetails).getUser().getRole();
        System.out.println(requestRole);
        System.out.println(userRole);
        if (!userRole.equals(requestRole)) {
            throw new Exception("User does not have the required role");
        }

        final String jwt = jwtUtil.generateToken(userDetails,userRole);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    
    
}
