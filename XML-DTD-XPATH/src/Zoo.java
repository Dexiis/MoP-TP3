import Alimentos.*;
import Animais.*;
import Funcionarios.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa um Zoológico.
 */
public class Zoo implements Serializable {
    String name;
    int precario;
    Carne carne = AlimentoSingleton.getCarneInstance(0);
    Palha palha = AlimentoSingleton.getPalhaInstance(0);
    Peixe peixe = AlimentoSingleton.getPeixeInstance(0);
    ArrayList<Animal> animais = new ArrayList<>();
    ArrayList<Funcionario> funcionarios = new ArrayList<>();
    ArrayList<Visitante> visitantes = new ArrayList<>();

    /**
     * Construtor da classe Zoo.
     *
     * @param name     Nome do zoológico
     * @param precario Preço de entrada do zoológico
     */
    public Zoo(String name, int precario) {
        this.name = name;
        this.precario = precario;
    }

    /**
     * Obtém o nome do zoológico.
     *
     * @return Nome do zoológico.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Define um novo nome para o zoológico.
     *
     * @param name Novo nome do zoológico.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o preço de entrada do zoológico.
     *
     * @return Preço de entrada.
     */
    public int getPrecario() {
        return this.precario;
    }

    /**
     * Define um novo preço de entrada para o zoológico.
     *
     * @param precario Novo preço de entrada.
     */
    public void setPrecario(int precario) {
        this.precario = precario;
    }

    /**
     * Verifica se existe comida para todos os animais de um tratador ou um animal específico.
     *
     * @param ID   O ID do tratador para verificar os animais associados.
     * @param name O nome do animal para verificar se existe comida suficiente, 'todos' para todos os animais.
     * @return true se houver comida para todos os animais ou para um animal em específico, false caso contrário.
     */
    public boolean requiredFoodToAnimal(int ID, String name) {
        int countCheck = 0, leoes = 0, elefantes = 0, girafas = 0, pinguins = 0;
        for (Funcionario funcionario : funcionarios)
            if (funcionario.getID() == ID) for (Object animal : ((Tratador) funcionario).getAnimaisAssociados()) {
                if (animal instanceof Leao) leoes++;
                else if (animal instanceof Elefante) elefantes++;
                else if (animal instanceof Girafa) girafas++;
                else if (animal instanceof Pinguim) pinguins++;
                if (name.equals("todos") && ((Animal) animal).requiredFood(carne.getQuantidade(), palha.getQuantidade(), peixe.getQuantidade(), leoes, elefantes, girafas, pinguins))
                    countCheck++;
                else if (((Animal) animal).getName().equals(name))
                    return ((Animal) animal).requiredFood(carne.getQuantidade(), palha.getQuantidade(), peixe.getQuantidade(), leoes, elefantes, girafas, pinguins);
                if (countCheck == ((Tratador) funcionario).getAnimaisAssociados().size()) return true;
            }
        return false;
    }

    /**
     * Dar comida para todos os animais de um tratador ou um animal específico.
     *
     * @param ID   O ID do tratador para verificar os animais associados.
     * @param name O nome do animal que receberá comida, 'todos' para todos os animais.
     */
    public void darComida(int ID, String name) {
        for (Funcionario funcionario : funcionarios)
            if (funcionario.getID() == ID) {
                for (Object animal : ((Tratador) funcionario).getAnimaisAssociados()) {
                    if (name.equals("todos")) {
                        if (animal instanceof Leao) carne.setQuantidade(carne.getQuantidade() - 6);
                        else if (animal instanceof Elefante) palha.setQuantidade(palha.getQuantidade() - 135);
                        else if (animal instanceof Girafa) palha.setQuantidade(palha.getQuantidade() - 66);
                        else if (animal instanceof Pinguim) peixe.setQuantidade(peixe.getQuantidade() - 3);
                    } else if (((Animal) animal).getName().equals(name)) {
                        if (animal instanceof Leao) carne.setQuantidade(carne.getQuantidade() - 6);
                        else if (animal instanceof Elefante) palha.setQuantidade(palha.getQuantidade() - 135);
                        else if (animal instanceof Girafa) palha.setQuantidade(palha.getQuantidade() - 66);
                        else if (animal instanceof Pinguim) peixe.setQuantidade(peixe.getQuantidade() - 3);
                        break;
                    }
                }
                System.out.println("Todos os animais foram alimentados com sucesso!");
                break;
            }
    }

    // ADDS E REMOVES

    /**
     * Adiciona um novo animal à lista.
     *
     * @param name   Nome do animal.
     * @param age    Idade do animal.
     * @param weigth Peso do animal.
     * @param type   Tipo do animal
     */
    public void addAnimais(String name, int age, int weigth, String type) {
        animais.add(AnimalFactory.getAnimal(name, age, weigth, type));
        System.out.println("O(A) " + type + " foi adicionado com sucesso!");
    }

    /**
     * Remove um animal da lista pelo nome.
     *
     * @param name Nome do animal a ser removido.
     */
    public void deleteAnimal(String name) {
        for (Animal animal : animais)
            if (animal.getName().equals(name)) {
                this.animais.remove(animal);
                System.out.println("O" + animal.getClass().getSimpleName() + "foi removido com sucesso!");
                break;
            }
    }

    /**
     * Modifica os atributos de um animal existente.
     *
     * @param name Nome do animal.
     * @param age  Nova idade do animal.
     * @param peso Novo peso do animal.
     */
    public void changeAnimal(String name, int age, int peso) {
        for (Animal animal : animais)
            if (animal.getName().equals(name)) {
                animal.setAge(age);
                animal.setPeso(peso);
                System.out.println("O " + animal.getClass().getSimpleName() + " foi mudado com sucesso!");
                break;
            }
    }

    /**
     * Adiciona um novo funcionário à lista.
     *
     * @param name       Nome do funcionário.
     * @param age        Idade do funcionário.
     * @param ID         ID do funcionário.
     * @param experience Experiencia do funcionário.
     * @param type       Tipo do funcionário.
     */
    public void addFuncionarios(String name, int age, int ID, int experience, String type) {
        funcionarios.add(EmployeeFactory.getFuncionario(name, age, ID, experience, type));
        System.out.println("O(A) " + type + " foi adicionado com sucesso!");
    }

    /**
     * Remove um funcionário da lista de funcionários com base no ID fornecido.
     *
     * @param ID Identificação única do funcionário a ser removido.
     */
    public void deleteFuncionario(int ID) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getID() == ID) {
                this.funcionarios.remove(funcionario);
                System.out.println("O " + funcionario.getClass().getSimpleName() + " foi mudado com sucesso!");
                break;
            }
        }
    }

    /**
     * Modifica as informações de um funcionário (idade e experiência) com base no ID fornecido.
     *
     * @param ID         Identificação única do funcionário a ser modificado.
     * @param age        Nova idade do funcionário.
     * @param experience Nova quantidade de experiência do funcionário.
     */
    public void changeFuncionarios(int ID, int age, int experience) {
        for (Funcionario funcionario : funcionarios)
            if (funcionario.getID() == ID) {
                funcionario.setAge(age);
                funcionario.setExperience(experience);
                System.out.println("O " + funcionario.getClass().getSimpleName() + " foi mudado com sucesso!");
                break;
            }
    }

    /**
     * Associa um animal ao tratador com base no ID do tratador, nome do animal.
     *
     * @param ID   Identificação única do tratador.
     * @param name Nome do animal a ser associado.
     */
    public void setAnimalTratador(int ID, String name) {
        for (Funcionario funcionario : funcionarios)
            if (funcionario instanceof Tratador && funcionario.getID() == ID) for (Animal animal : animais)
                if (animal.getName().equals(name)) {
                    ((Tratador) funcionario).setAnimal(animal);
                    System.out.println("Animal associado com sucesso!");
                }
    }

    /**
     * Remove a associação de um animal com o tratador com base no ID do tratador, nome do animal.
     *
     * @param ID   Identificação única do tratador.
     * @param name Nome do animal a ser desassociado.
     */
    public void removeAnimalTratador(int ID, String name) {
        for (Funcionario funcionario : funcionarios)
            if (funcionario instanceof Tratador && funcionario.getID() == ID) for (Animal animal : animais)
                if (animal.getName().equals(name)) {
                    ((Tratador) funcionario).removeAnimal(animal);
                    System.out.println("Animal desassociado com sucesso!");
                }
    }

    /**
     * Adiciona um novo visitante à lista de visitantes.
     *
     * @param name Nome do visitante a ser adicionado.
     * @param ID   Identificação única do visitante.
     * @param age  Idade do visitante.
     */
    public void addVisitante(String name, int ID, int age) {
        visitantes.add(new Visitante(name, age, ID));
        System.out.println("O visitante foi adicionado com sucesso!");
    }

    /**
     * Remove um visitante da lista de visitantes com base no ID fornecido.
     *
     * @param ID Identificação única do visitante a ser removido.
     */
    public void deleteVisitante(int ID) {
        for (Visitante x : visitantes) {
            if (x.getID() == ID) {
                this.visitantes.remove(x);
                break;
            }
        }
        System.out.println("O visitante foi removido com sucesso!");
    }

    /**
     * Adiciona uma quantidade de alimento ao stock.
     *
     * @param peso Quantidade de alimento a ser adicionada.
     * @param type Tipo de alimento.
     */
    public void addAlimento(int peso, String type) {
        if (type.equals("carne")) carne.setQuantidade(carne.getQuantidade() + peso);
        if (type.equals("palha")) palha.setQuantidade(palha.getQuantidade() + peso);
        if (type.equals("peixe")) peixe.setQuantidade(peixe.getQuantidade() + peso);
        System.out.println("A(O) " + type + " foi adicionada(o) com sucesso!");
    }

    /**
     * Remove uma quantidade de alimento do stock.
     *
     * @param peso Quantidade de alimento a ser removido.
     */
    public void removeAlimento(int peso, String type) {
        if (type.equals("carne") && carne.getQuantidade() - peso >= 0) {
            carne.setQuantidade(carne.getQuantidade() - peso);
            System.out.println("A carne foi removida com sucesso!");
        } else System.out.print("Esse valor é inválido.");
        if (type.equals("palha") && palha.getQuantidade() - peso >= 0) {
            palha.setQuantidade(palha.getQuantidade() - peso);
            System.out.println("A palha foi removida com sucesso!");
        } else System.out.print("Esse valor é inválido.");
        if (type.equals("peixe") && peixe.getQuantidade() - peso >= 0) {
            peixe.setQuantidade(peixe.getQuantidade() - peso);
            System.out.println("O peixe foi removido com sucesso!");
        } else System.out.print("Esse valor é inválido.");
    }

    //  VALIDATES

    /**
     * Valida se um animal com o nome fornecido caso exista na lista de animais.
     *
     * @param name Nome do animal a ser verificado.
     * @return true se o animal existir, false caso contrário.
     */
    public boolean validateAnimal(String name) {
        for (Animal animal : animais) if (animal.getName().equals(name)) return true;
        return false;
    }

    /**
     * Valida se um ID fornecido já existe na lista de funcionarios ou de visitantes.
     *
     * @param ID Identificação do funcionario/visitante a ser verificado.
     * @return true se o funcionario/visitante existir, false caso contrário.
     */
    public boolean validateID(int ID) {
        for (Funcionario funcionario : funcionarios) if (funcionario.getID() == ID) return true;
        for (Visitante visitante : visitantes) if (visitante.getID() == ID) return true;
        return false;
    }

    /**
     * Valida se um tratador com o ID fornecido tem um animal associado com o nome e tipo específicos.
     * O procedimento especifica o tipo de animal a ser verificado (Leão, Elefante, Girafa, ou Pinguim).
     *
     * @param ID   O ID do tratador a ser verificado.
     * @param name O nome do animal a ser verificado.
     * @return true se o tratador tiver o animal associado, false caso contrário.
     */
    public boolean validateAnimalInTratador(int ID, String name) {
        if (validateID(ID) && validateAnimal(name)) for (Funcionario funcionario : funcionarios)
            if (funcionario instanceof Tratador && funcionario.getID() == ID) {
                return ((Tratador) funcionario).checkAnimalInTratador(name);
            }
        return false;
    }

    /**
     * Retorna uma representação textual do zoológico.
     *
     * @return String representando o zoológico.
     */
    public String toString() {
        return "O Zoo " + this.name + " tem o preço de entrada de " + this.precario + "$.";
    }

    // PRINTS

    /**
     * Imprime informações sobre o zoológico, incluindo todos os animais, tratadores, administradores,
     * guias e visitantes, bem como a quantidade de carne, palha e peixe disponíveis.
     */
    public void printZoo() {
        System.out.println();
        printAnimais("Leões");
        printAnimais("Elefantes");
        printAnimais("Girafas");
        printAnimais("Pinguins");
        printFuncionarios("Administradores");
        printFuncionarios("Guias");
        printFuncionarios("Tratadores");
        printVisitantes();
        System.out.println(carne);
        System.out.println(palha);
        System.out.println(peixe);
    }

    /**
     * Imprime todos os animais do tipo/dieta requeridos presentes no zoológico.
     *
     * @param choice Tipo ou dieta que será mostrado.
     */
    public void printAnimais(String choice) {
        for (Animal animal : animais) {
            if (animal.getType().equals(choice) || animal.getDiet().equals(choice) || (choice.equals("Leões") && (animal instanceof Leao)) || (choice.equals("Elefantes") && (animal instanceof Elefante)) || (choice.equals("Girafas") && (animal instanceof Girafa)) || (choice.equals("Pinguins") && (animal instanceof Pinguim)))
                System.out.println(animal);
        }
    }

    /**
     * Imprime todos os animais que tenham maior idade do requerido, presentes no zoológico.
     *
     * @param idade Idade a ser comparada.
     */
    public void printAnimais(int idade) {
        System.out.println("Estes são os animais com mais de " + idade + " anos.");
        for (Animal animal : animais)
            if (animal.getAge() >= idade) System.out.println(animal);
    }

    /**
     * Imprime todos os funcionarios do tipo requerido presentes no zoológico.
     *
     * @param choice Tipo que será mostrado.
     */
    public void printFuncionarios(String choice) {
        for (Funcionario funcionario : funcionarios) {
            if ((choice.equals("Administradores") && (funcionario instanceof Administrador)) || (choice.equals("Guias") && (funcionario instanceof Guia)))
                System.out.println(funcionario);
            if ((choice.equals("Tratadores") && (funcionario instanceof Tratador))) {
                System.out.println(funcionario);
                ((Tratador) funcionario).AnimaisAssociadosToString();
            }
        }
    }

    /**
     * Imprime informações sobre todos os tratadores presentes no zoológico.
     */
    public void printVisitantes() {
        for (Visitante visitante : visitantes)
            System.out.println(visitante.toString());
    }
}