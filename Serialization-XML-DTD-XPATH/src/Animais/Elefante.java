package Animais;

import java.io.Serializable;

/**
 * Representa um elefante com nome, idade e weight.
 */
public class Elefante extends Animal implements Serializable {

    /**
     * Construtor da classe Elefante.
     *
     * @param name   Nome do elefante.
     * @param age    Idade do elefante.
     * @param weight Peso do elefante em Kg.
     * @param diet   Dieta do elefante.
     * @param type   Tipo do elefante.
     */
    public Elefante(String name, int age, int weight, String diet, String type) {
        super(name, age, weight, diet, type);
    }

    public boolean requiredFood(int carne, int palha, int peixe, int leoes, int elefantes, int girafas, int pinguins) {
        return palha - 135 * elefantes >= 0;
    }
}