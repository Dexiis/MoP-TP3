package Animais;

import java.io.Serializable;

/**
 * Representa um pinguim com nome, idade e peso.
 */
public class Pinguim extends Animal implements Serializable {

    /**
     * Construtor da classe Pinguim.
     *
     * @param name   Nome do pinguim.
     * @param age    Idade do pinguim.
     * @param weight Peso do pinguim em Kg.
     * @param diet   Dieta do pinguim.
     * @param type   Tipo do pinguim.
     */
    public Pinguim(String name, int age, int weight, String diet, String type) {
        super(name, age, weight, diet, type);
    }

    public boolean requiredFood(int carne, int palha, int peixe, int leoes, int elefantes, int girafas, int pinguins) {
        return peixe - 3 * pinguins >= 0;
    }
}
