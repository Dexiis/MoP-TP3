package Alimentos;

import java.io.Serializable;

/**
 * Classe que representa uma quantidade de carne disponível no jardim zoológico.
 */
public class Carne extends Alimento implements Serializable {

    /**
     * Construtor da classe Carne.
     *
     * @param quantidade Quantidade inicial de carne em quilogramas.
     */
    public Carne(int quantidade) {
        super(quantidade);
    }
}