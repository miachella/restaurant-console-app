package dev.ihm.options;

import org.springframework.stereotype.Service;

import dev.exception.PlatException;

@Service
public class OptionTerminer implements IOptionMenu {
    @Override
    public String getTitre() {
        return "Terminer";
    }

    @Override
    public void executer() {
        throw new PlatException("Aurevoir");
    }
}
