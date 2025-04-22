package Alimentos;

public class AlimentoSingleton {
    private static Carne carneInstance;
    private static Palha palhaInstance;
    private static Peixe peixeInstance;

    private AlimentoSingleton() {}

    public static Carne getCarneInstance(int quantidade) {
        if (carneInstance == null) {
            carneInstance = new Carne(quantidade);
        }
        return carneInstance;
    }

    public static Palha getPalhaInstance(int quantidade) {
        if (palhaInstance == null) {
            palhaInstance = new Palha(quantidade);
        }
        return palhaInstance;
    }

    public static Peixe getPeixeInstance(int quantidade) {
        if (peixeInstance == null) {
            peixeInstance = new Peixe(quantidade);
        }
        return peixeInstance;
    }
}

