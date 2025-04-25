package Alimentos;

import java.io.Serializable;

/**
 * Classe que representa uma quantidade de peixe disponível no jardim zoológico.
 */
public class Peixe extends Alimento implements Serializable {
    private static Peixe instance = null;

    /**
     * Construtor da classe Peixe.
     *
     * @param quantidade Quantidade inicial de peixe em quilogramas.
     */
    private Peixe(int quantidade) {
        super(quantidade);
    }

    public static Peixe getPeixeInstance(int quantidade) {
        if (instance == null) {
            instance = new Peixe(quantidade);
        }
        return instance;
    }
}