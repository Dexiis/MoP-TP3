package Alimentos;

import java.io.Serializable;

/**
 * Classe que representa uma quantidade de palha disponível no jardim zoológico.
 */
public class Palha extends Alimento implements Serializable {

    /**
     * Construtor da classe Palha.
     *
     * @param quantidade Quantidade inicial de palha em quilogramas.
     */
    public Palha(int quantidade) {
        super(quantidade);
    }
}