package Alimentos;

/**
 * Classe que representa uma quantidade de carne disponível no jardim zoológico.
 */
public class Carne extends Alimento {
    private static Carne instance = null;

    /**
     * Construtor da classe Carne.
     *
     * @param quantidade Quantidade inicial de carne em quilogramas.
     */
    private Carne(int quantidade) {
        super(quantidade);
    }

    public static Carne getCarneInstance(int quantidade) {
        if (instance == null) {
            instance = new Carne(quantidade);
        }
        return instance;
    }
}