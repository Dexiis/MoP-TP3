package Alimentos;

import java.io.Serializable;

/**
 * Classe que representa uma quantidade de peixe disponível no jardim zoológico.
 */
public class Peixe extends Alimento implements Serializable {

    /**
     * Construtor da classe Peixe.
     *
     * @param quantidade Quantidade inicial de peixe em quilogramas.
     */
    public Peixe(int quantidade) {
        super(quantidade);
    }
}