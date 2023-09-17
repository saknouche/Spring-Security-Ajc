package com.sadev.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // activation de la configuration personnalisée de la sécurité
public class WebSecurityConfig {
	

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	// TODO: PasswordEncoder (Etpae en premier)
	// Initialisation d'une instance de Bcrypt pour hasher les mots de passes
	@Bean
	PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

	// Définition des filtres sur les UrlS
	// Accès en fonction d'un état authentifié ou non
	// Accès en fonction de l'utilisateur authentifié (désactivé, expiré...)
	// accès en fonction des roles

	// On va être en stateLess => on ne veut pas que le serveur garde en mémoire la
	// session
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// Désactiver le CORS
		http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {

			@Override
			public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {

				httpSecurityCorsConfigurer.disable();
			}
		});

		// Désactiver csrf
		http.csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {

			@Override
			public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
				// TODO Auto-generated method stub
				httpSecurityCorsConfigurer.disable();

			}
		});

		http.authorizeHttpRequests(requests -> {
			requests
					// Toutes les requêtes HTTP /api/users sont autorisées pour tout le monde
					// ()authentifié ou non
					.requestMatchers("/api/auth/*").permitAll()
					// Toutes les autres requêtes HTTP nécessitent une authentification
					.anyRequest().authenticated();
		});

		// Configuration de la session Spring Security: AUCUNE session ne sera créee
		// câté serveur
		// Moins coûteux et inutile lorsqu'on est dans une config RestFull
		http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
			httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		});

		// Avant d'accéder à la logique -> Ajouter un filrre personnalisée qui s'exécute
		// avant le filtre userrnamePasswordAuthenticationFilter
		// Filtre pour gérée pour l'authentification basée sur la Jwt reçu dans les
		// entêtes des requêtes
		// userrnamePasswordAuthenticationFilte est un filtre de base de Spring
		// Il est exécuté pour gérer l'auth par MDP et username

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	AuthJwtFilter authenticationJwtTokenFilter() {
		// TODO Auto-generated method stub
		return new AuthJwtFilter();
	}
}
