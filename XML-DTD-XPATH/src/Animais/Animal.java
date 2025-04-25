package Animais;

import java.util.Objects;

public abstract class Animal {
    private final String name;
    private int age;
    private int weight;
    private final String diet;
    private final String type;

    /**
     * Construtor da classe Animal.
     *
     * @param name Nome do animal.
     */
    public Animal(String name, int age, int weight, String diet, String type) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.diet = diet;
        this.type = type;
    }

    /**
     * Obtém o nome do animal.
     *
     * @return O nome do animal.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém a idade dos animal.
     *
     * @return A idade dos animal.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Obtém o peso do animal.
     *
     * @return O peso do animal em Kg.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Obtém a dieta do animal.
     *
     * @return A dieta do animal em Kg.
     */
    public String getDiet() {
        return this.diet;
    }

    /**
     * Obtém o tipo do animal.
     *
     * @return O tipo do animal em Kg.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Define a idade do animal.
     *
     * @param age A nova idade do animal.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Define o peso do animal.
     *
     * @param weight O novo peso do animal em Kg.
     */
    public void setPeso(int weight) {
        this.weight = weight;
    }


    /**
     * Confirma se há comida suficiente em “stock” para alimentar o animal.
     *
     * @param carne     Stock existente de carne.
     * @param palha     Stock existente de palha.
     * @param peixe     Stock existente de peixe.
     * @param leoes     Número de Leões.
     * @param elefantes Número de Elefantes.
     * @param girafas   Número de Girafas.
     * @param pinguins  Número de Pinguins.
     */
    public abstract boolean requiredFood(int carne, int palha, int peixe, int leoes, int elefantes, int girafas, int pinguins);

    /**
     * Compara este animal com outro para verificar se são iguais.
     *
     * @param object O objeto a ser comparado.
     * @return true se o animal possuir o mesmo nome, idade e peso; false caso contrário, seja de uma classe diferente ou null.
     */
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Animal animais = (Animal) object;
        return age == animais.age && weight == animais.weight && Objects.equals(name, animais.name);
    }

    /**
     * Retorna uma representação textual do animal.
     *
     * @return Uma string formatada contendo o nome, idade e peso do animal.
     */
    public String toString() {
        return "O " + getClass().getSimpleName() + " " + this.name + " de " + this.age + " anos, pesa " + this.weight + "Kg.";
    }
}