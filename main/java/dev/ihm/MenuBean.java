package dev.ihm;

import dev.exception.PlatException;
import dev.ihm.options.IOptionMenu;
import dev.ihm.options.OptionAjouterPlat;
import dev.ihm.options.OptionListerPlats;
import dev.ihm.options.OptionTerminer;
import dev.service.IPlatService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class MenuBean {

    private Map<Integer, IOptionMenu> actions = new HashMap<>();

    private String menu;
    private Scanner scanner;

    public MenuBean(Scanner scanner, IPlatService service, ApplicationContext context) {
    	
			actions.put(1, context.getBean(OptionListerPlats.class));
			actions.put(2, context.getBean(OptionAjouterPlat.class));
			actions.put(99, context.getBean(OptionTerminer.class));
    	
        this.scanner = scanner;
    }

    public void afficher() {

        boolean continuer = true;

        while (continuer) {

            System.out.println(getMenuTexte());

            int choix = this.scanner.nextInt();

            try {
                this.actions.get(choix).executer();
            } catch (PlatException e) {
                continuer = false;
                System.out.println(e.getMessage());
            }
        }
    }

    private String getMenuTexte() {
        if (menu == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("** Restaurant Console App **");
            sb.append("\n");
            this.actions.forEach((index, option) -> {
                sb.append(index);
                sb.append(". ");
                sb.append(option.getTitre());
                sb.append("\n");
            });
            this.menu = sb.toString();
        }
        return this.menu;
    }
}

