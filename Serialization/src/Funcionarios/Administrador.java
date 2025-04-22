package Funcionarios;

import java.io.Serializable;

/**
 * Classe que representa um administrador do jardim zoológico.
 * Cada administrador tem um nome, idade, identificação única e anos de experiência.
 */
public class Administrador extends Funcionario implements Serializable {

    /**
     * Construtor da classe Administrador.
     *
     * @param name       Nome do administrador.
     * @param age        Idade do administrador.
     * @param ID         Identificação única do administrador.
     * @param experience Anos de experiência do administrador.
     */
    public Administrador(String name, int age, int ID, int experience) {
        super(name, age, ID, experience);
    }
}
