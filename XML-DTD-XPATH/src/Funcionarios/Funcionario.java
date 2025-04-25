package Funcionarios;

import java.util.Objects;

/**
 * Classe que representa um funcionário do jardim zoológico.
 * Cada funcionário tem um nome, idade, identificação única e anos de experiência.
 */

public class Funcionario {
    private final String name;
    private int age;
    private final int ID;
    private int experience;

    /**
     * Construtor da classe Funcionário.
     *
     * @param name       Nome do funcionário.
     * @param age        Idade do funcionário.
     * @param ID         Identificação única do funcionário.
     * @param experience Anos de experiência do funcionário.
     */
    public Funcionario(String name, int age, int ID, int experience) {
        this.name = name;
        this.age = age;
        this.ID = ID;
        this.experience = experience;
    }

    /**
     * Obtém o nome do funcionário.
     *
     * @return Nome do funcionário.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém a idade do funcionário.
     *
     * @return Idade do funcionário.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Obtém o ID do funcionário.
     *
     * @return Identificação única do funcionário.
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Obtém a experiência do funcionário.
     *
     * @return Anos de experiência do funcionário.
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * Define a idade do funcionário.
     *
     * @param age Nova idade do funcionário.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Define a experiência do funcionário.
     *
     * @param experience Novo valor de anos de experiência.
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Compara dois funcionários verificando se têm os mesmos atributos.
     *
     * @param object Outro funcionário a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Funcionario that = (Funcionario) object;
        return age == that.age && ID == that.ID && experience == that.experience && Objects.equals(name, that.name);
    }

    /**
     * Retorna uma representação textual do funcionário.
     *
     * @return String contendo informações do funcionário.
     */
    public String toString() {
        return "O " + getClass().getSimpleName() + " " + this.name + " de " + this.age + " anos possuí " + this.experience + " anos de experiencia. Número de identificação: " + this.ID;
    }
}
