/**
 * Classe que representa um visitante do jardim zoológico.
 * Cada visitante tem um nome, idade e um número de identificação único (bilhete).
 */
public class Visitante {
    private final String name;
    private final int age;
    private final int ID;

    /**
     * Construtor da classe Visitante.
     *
     * @param name Nome do visitante.
     * @param age  Idade do visitante.
     * @param ID   Número de identificação do bilhete do visitante.
     */
    public Visitante(String name, int age, int ID) {
        this.name = name;
        this.age = age;
        this.ID = ID;
    }

    /**
     * Obtém o nome do visitante.
     *
     * @return Nome do visitante.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém a idade do visitante.
     *
     * @return Idade do visitante.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Obtém o número de identificação do visitante.
     *
     * @return Número de identificação do bilhete do visitante.
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Compara dois visitantes verificando se têm os mesmos atributos.
     *
     * @param visitante Outro visitante a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    public boolean equals(Visitante visitante) {
        return (visitante != null) && (visitante.getName().equals(getName())) && (visitante.getAge() == getAge()) && (visitante.getID() == getID());
    }

    /**
     * Retorna uma representação textual do visitante.
     *
     * @return String contendo informações do visitante.
     */
    public String toString() {
        return "O(A) visitante " + this.name + " de " + this.age + " anos com bilhete número " + this.ID + ".";
    }
}
