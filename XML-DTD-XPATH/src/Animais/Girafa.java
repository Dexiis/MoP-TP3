package Animais;

/**
 * Representa uma girafa com nome, idade e peso.
 */
public class Girafa extends Animal {

    /**
     * Construtor da classe Girafa.
     *
     * @param name   Nome da girafa.
     * @param age    Idade da girafa.
     * @param weight Peso da girafa em Kg.
     * @param diet   Dieta da girafa.
     * @param type   Tipo do girafa.
     */
    public Girafa(String name, int age, int weight, String diet, String type) {
        super(name, age, weight, diet, type);
    }

    public boolean requiredFood(int carne, int palha, int peixe, int leoes, int elefantes, int girafas, int pinguins) {
        return palha - 66 * girafas >= 0;
    }
}