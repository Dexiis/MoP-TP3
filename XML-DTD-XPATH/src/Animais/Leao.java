package Animais;

/**
 * Representa um leão com nome, idade e peso.
 */
public class Leao extends Animal {

    /**
     * Construtor da classe Leao.
     *
     * @param name   Nome do leão.
     * @param age    Idade do leão.
     * @param weight Peso do leão em Kg.
     * @param diet   Dieta do Leão.
     * @param type   Tipo do Leão.
     */
    public Leao(String name, int age, int weight, String diet, String type) {
        super(name, age, weight, diet, type);
    }

    public boolean requiredFood(int carne, int palha, int peixe, int leoes, int elefantes, int girafas, int pinguins) {
        return carne - 6 * leoes >= 0;
    }
}