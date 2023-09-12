package com.sadev.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //activation de la configuration personnalisée de la sécurité
public class WebSecurityConfig {

	//Définition des filtres sur les UrlS
	//Accès en fonction d'un état authentifié ou non
	//Accès en fonction de l'utilisateur authentifié (désactivé, expiré...)
	//accès en fonction des roles
	
	//On va être en stateLess => on ne veut pas que le serveur garde en mémoire la session
	
}
