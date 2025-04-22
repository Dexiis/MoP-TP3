package Funcionarios;

public interface IAnimalManager {

    /**
     * Adiciona um animal à lista de animais sob responsabilidade do tratador.
     *
     * @param animal Objeto do animal a ser adicionado.
     */
    void setAnimal(Object animal);

    /**
     * Remove um animal da lista de animais sob responsabilidade do tratador.
     *
     * @param animal Objeto do animal a ser removido.
     */
    void removeAnimal(Object animal);

    /**
     * Verifica se um animal específico está associado ao tratador.
     *
     * @param name Nome do animal.
     * @return true se o animal estiver associado ao tratador, false caso contrário.
     */
    boolean checkAnimalInTratador(String name);

    /**
     * Escreve todos os animais associados ao Funcionarios.Tratador.
     */
    void AnimaisAssociadosToString();
}
