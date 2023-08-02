package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	 // Expose AuthenticationTrustResolver bean
    @Bean
    public AuthenticationTrustResolver authenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
 
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .requestMatchers("/", "/register", "/login").permitAll() // Allow public access to these pages
            .requestMatchers("/list-users", "/edit_user").authenticated() // Allow access to these pages only to authenticated users
            .and()
            .formLogin()
            .loginPage("/login").permitAll()// Set the login page URL
            .successHandler(loginSuccessHandler())
           // .defaultSuccessUrl("/welcome")// Redirect to the list-users page after successful login
            .and()
            .logout()
            .logoutSuccessUrl("/") // Redirect to the homepage after successful logout
            .and()
            .csrf().disable(); // Disable CSRF for simplicity (you should enable it in production)
        
        return http.build();
    }

    
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Here you can configure your authentication provider
        // For simplicity, we won't implement it in this example
    }
    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/welcome");
    }
}
