package Funcionarios;

import Animais.Animal;

import java.util.ArrayList;

/**
 * Classe que representa um tratador de animais num jardim zoológico.
 * Cada tratador tem um nome, idade, identificação única e anos de experiência.
 */
public class Tratador extends Funcionario implements IAnimalManager, java.io.Serializable {
    private final ArrayList<Animal> animais = new ArrayList<>();

    /**
     * Construtor da classe Tratador.
     *
     * @param name       Nome do tratador.
     * @param age        Idade do tratador.
     * @param ID         Identificação única do tratador.
     * @param experience Anos de experiência do tratador.
     */
    public Tratador(String name, int age, int ID, int experience) {
        super(name, age, ID, experience);
    }

    /**
     * Obtém a lista de animais associados ao tratador.
     *
     * @return A lista de animais associados ao tratador.
     */
    public ArrayList<Animal> getAnimaisAssociados() {
        return animais;
    }

    @Override
    public void setAnimal(Animal animal) {
        animais.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animais.remove(animal);
    }

    @Override
    public boolean checkAnimalInTratador(String name) {
        for (Animal animal : animais) if (animal.getName().equals(name)) return true;
        return false;
    }

    @Override
    public void AnimaisAssociadosToString() {
        for (Animal animal : animais)
            System.out.println("     O " + animal.getClass().getSimpleName() + " " + (animal.getName() + " pertence a " + getName() + "."));
    }
}

