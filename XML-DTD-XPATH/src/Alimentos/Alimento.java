package Alimentos;

import java.io.Serializable;

public abstract class Alimento implements Serializable {
    private int quantidade;

    /**
     * Construtor da classe Alimentos.
     *
     * @param quantidade Quantidade inicial de alimentos em quilogramas.
     */
    public Alimento(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Obtém a quantidade do alimento disponível.
     *
     * @return Quantidade do alimento em quilogramas.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade do alimento disponível.
     *
     * @param quantidade Nova quantidade do alimento em quilogramas.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Compara este alimento com outro para verificar se são iguais.
     *
     * @param object O objeto a ser comparado.
     * @return true se os alimentos possuírem a mesma quantidade; false caso contrário, seja de uma classe diferente ou null.
     */
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Alimento alimentos = (Alimento) object;
        return quantidade == alimentos.quantidade;
    }

    /**
     * Retorna uma representação textual da quantidade do alimento.
     *
     * @return String contendo a quantidade do alimento disponível.
     */
    public String toString() {
        return "Existe " + this.quantidade + "Kg de " + getClass().getSimpleName() + ".";
    }
}
