package com.goldtek.erp_plugin.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import com.goldtek.qms2.service.AuthorizeService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;  
//	@Autowired
//	private AuthorizeService authorizeService;	

	
		
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        

    	
    	
    	httpSecurity
                //.authorizeHttpRequests(registry -> registry
                //                .requestMatchers("/authorize/**").hasRole("Admin")
                //                .anyRequest().authenticated()
                //)
        		.authorizeHttpRequests(registry -> registry
                        .anyRequest().permitAll()
//                        .authenticated()
        		)

                .oauth2ResourceServer(oauth2Configurer -> oauth2Configurer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
                    Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
                    Collection<String> roles = realmAccess.get("roles");
                    System.out.println(roles);
                    var grantedAuthorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .toList();
                    return new JwtAuthenticationToken(jwt, grantedAuthorities);
                }))
                .authenticationEntryPoint(restAuthenticationEntryPoint)	
                );
        
    	httpSecurity.cors();
        
        return httpSecurity.build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    

}