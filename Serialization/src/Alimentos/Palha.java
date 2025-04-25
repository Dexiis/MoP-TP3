package Alimentos;

import java.io.Serializable;

/**
 * Classe que representa uma quantidade de palha disponível no jardim zoológico.
 */
public class Palha extends Alimento implements Serializable {
    private static Palha instance = null;

    /**
     * Construtor da classe Palha.
     *
     * @param quantidade Quantidade inicial de palha em quilogramas.
     */
    private Palha(int quantidade) {
        super(quantidade);
    }

    public static Palha getPalhaInstance(int quantidade) {
        if (instance == null) {
            instance = new Palha(quantidade);
        }
        return instance;
    }
}