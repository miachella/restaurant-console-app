package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import dev.config.AppConfig;
import dev.ihm.MenuBean;

public class AppSpringJava {
	
	public static void main(String[] args) {
		
		// Création du contexte Spring à partir d'une configuration Java
		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()){
			context.getEnvironment().setActiveProfiles("jdbc");
			context.register(AppConfig.class);
			context.refresh();
		
			// récupération du bean Menu
			MenuBean menu = context.getBean(MenuBean.class);
			menu.afficher();
			// fermeture du Scanner
			context.getBean(Scanner.class).close();
			// fermeture du contexte Spring
			context.close();
		} catch (DataAccessException e) {
			System.out.println("Problème d'accès à la base de données.");
		}
	}

}
