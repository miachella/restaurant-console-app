package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.AppConfig;
import dev.ihm.MenuBean;

public class AppSpringJava {
	
	public static void main(String[] args) {
		// Création du contexte Spring à partir d'une configuration Java
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("Fich2");
		context.register(AppConfig.class);
		context.refresh();
		
		// récupération du bean Menu
		MenuBean menu = context.getBean(MenuBean.class);
		menu.afficher();
		// fermeture du Scanner
		context.getBean(Scanner.class).close();
		// fermeture du contexte Spring
		context.close();
		}

}
