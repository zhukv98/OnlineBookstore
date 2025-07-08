package com.example.demo.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	// @Autowired
	// private IUserService userService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
	}
            
	// @Bean
	// public AuthenticationProvider authenticationProvider() {
	// 	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userService);
	// 	authProvider.setPasswordEncoder(passwordEncoder());
	// 	return authProvider;
	// }
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/js/**", "/css/**", "/img/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login").permitAll()
			)
			.logout(logout -> logout
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout").permitAll()
			);

		return http.build();
	}
}
