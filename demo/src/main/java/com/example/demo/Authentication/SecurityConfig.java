package com.example.demo.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

//import com.kpit.demoproj.Service.JwtTokenProvider;

@Configuration
@EnableMethodSecurity
public class SecurityConfig  {

    private CustomAuthenticationProvider authenticationProvider;
    SecurityConfig(CustomAuthenticationProvider authenticationProvider1){
        this.authenticationProvider=authenticationProvider1;
    }

    
    @Autowired
    private JwtRequestFilter jwtrequestFilter;

   @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
        .cors((cors)->cors.configurationSource(corsConfigurationSource()))
        //    .cors(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/login/**").permitAll()
                .requestMatchers("/admin/*").hasAuthority("ADMIN")
                .requestMatchers("/tpm/**").hasAuthority("TPM")
                // .requestMatchers("/common/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            )
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //   .authenticationProvider(authenticationProvider)
          .addFilterBefore(
          jwtrequestFilter,
              UsernamePasswordAuthenticationFilter.class
           );

        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
  
    
    @Bean
CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

// @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/**").allowedOrigins("*");
//             }
//         };
//     }

}

