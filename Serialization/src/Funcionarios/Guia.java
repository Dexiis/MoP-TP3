package Funcionarios;

import java.io.Serializable;

/**
 * Classe que representa um guia do jardim zoológico.
 * Cada guia tem um nome, idade, identificação única e anos de experiência.
 */
public class Guia extends Funcionario implements Serializable {

    /**
     * Construtor da classe Guia.
     *
     * @param name       Nome do guia.
     * @param age        Idade do guia.
     * @param ID         Identificação única do guia.
     * @param experience Anos de experiência do guia.
     */
    public Guia(String name, int age, int ID, int experience) {
        super(name, age, ID, experience);
    }
}