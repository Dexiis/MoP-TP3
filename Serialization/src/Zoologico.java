import java.io.*;
import java.util.Scanner;

public class Zoologico {
    static Zoo zoo = Zoo.getZooInstance("", 0);
    static String ficheiro = "BaseDados";

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;

        while (invalid) {
            System.out.println("Deseja criar um novo zoo ou importar um já existente?");
            System.out.println("1 - Criar um novo zoo.");
            System.out.println("2 - Importar um zoo existente.");
            switch (input.nextLine()) {
                case "1":
                    System.out.println("Pretende começar com um zoo predefinido? (Escreva 'n' para um zoo sem nada)");
                    if (!input.nextLine().equals("n")) {
                        carregarZoo("ZooPredefinido");
                        invalid = false;
                        break;
                    }
                    System.out.println("Dê um nome para o seu zoo: ");
                    zoo.setName(input.nextLine());
                    System.out.println("Qual será o preçario para o seu zoo:");
                    zoo.setPrecario(Integer.parseInt(input.nextLine()));
                    invalid = false;
                    break;
                case "2":
                    System.out.println("Qual é o nome do ficheiro que contem o seu zoo?");
                    invalid = carregarZoo(input.nextLine());
                    break;
                default:
                    System.out.println("Essa opção é inválida.");
                    break;
            }
        }
        while (true) {
            System.out.println("\n----- Bem vindo à administração do(a) " + zoo.getName() + "!!! Preçario atual: " + zoo.getPrecario() + "$ -----");
            System.out.println("O que deseja visualizar?");
            System.out.println("1 - Stock de alimentos.");
            System.out.println("2 - Animais.");
            System.out.println("3 - Funcionários/Visitantes.");
            System.out.println("4 - Zoológico.");
            System.out.println("5 - Salvar o zoo.");
            System.out.println("6 - Terminar todas as operações e salvar o zoo.");

            switch (input.nextLine()) {
                case "1" -> listaRequerida(1);
                case "2" -> listaRequerida(2);
                case "3" -> listaRequerida(3);
                case "4" -> listaRequerida(4);
                case "5" -> salvarZoo();
                case "6" -> encerrarPrograma();
                default -> System.out.println("Essa opção não é válida.");
            }
        }
    }

    public static void criarZooPredefinido() {
        zoo.addAnimais("Simba", 14, 200, "leão");
        zoo.addAnimais("César", 40, 5500, "elefante");
        zoo.addAnimais("Giraldina", 17, 600, "girafa");
        zoo.addAnimais("Dave", 12, 30, "pinguim");
        zoo.addFuncionarios("Artur", 19, 52553, 3, "tratador");
        zoo.setAnimalTratador(52553, "Simba");
        zoo.setAnimalTratador(52553, "César");
        zoo.addFuncionarios("Vermelhudo", 19, 51475, 3, "tratador");
        zoo.setAnimalTratador(51475, "Giraldina");
        zoo.setAnimalTratador(51475, "Dave");
        zoo.addFuncionarios("David", 20, 69697, 2, "guia");
        zoo.addFuncionarios("Bernardo", 20, 57489, 4, "administrador");
        zoo.addVisitante("Bea", 62704, 18);
        zoo.addVisitante("Duarte", 32442, 32);
        zoo.addVisitante("Lara", 43760, 25);
        zoo.addAlimento(6 * 3, "carne");
        zoo.addAlimento(135 * 3 + 66 * 3, "palha");
        zoo.addAlimento(3 * 3, "peixe");
    }

    public static void listaRequerida(int requerimento) {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        System.out.println("\nEscolha uma opção: ");
        if (requerimento == 1) {
            System.out.println("1 - Carne");
            System.out.println("2 - Palha");
            System.out.println("3 - Peixe");
            System.out.println("4 - Voltar para trás.");
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1" -> lista2Requerida(1);
                    case "2" -> lista2Requerida(2);
                    case "3" -> lista2Requerida(3);
                    case "4" -> System.out.println();
                    default -> invalid = InvalidText();
                }
            }
        }
        if (requerimento == 2) {
            System.out.println("1 - Leões");
            System.out.println("2 - Elefantes");
            System.out.println("3 - Girafas");
            System.out.println("4 - Pinguins");
            System.out.println("5 - Voltar para trás.");
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1" -> lista2Requerida(4);
                    case "2" -> lista2Requerida(5);
                    case "3" -> lista2Requerida(6);
                    case "4" -> lista2Requerida(7);
                    case "5" -> System.out.println();
                    default -> invalid = InvalidText();
                }
            }
        }
        if (requerimento == 3) {
            System.out.println("1 - Tratadores");
            System.out.println("2 - Administradores");
            System.out.println("3 - Guias");
            System.out.println("4 - Visitantes");
            System.out.println("5 - Voltar para trás.");
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1" -> lista2Requerida(8);
                    case "2" -> lista2Requerida(9);
                    case "3" -> lista2Requerida(10);
                    case "4" -> lista2Requerida(11);
                    case "5" -> System.out.println();
                    default -> invalid = InvalidText();
                }
            }
        }
        if (requerimento == 4) {
            System.out.println("1 - Zoo inteiro.");
            System.out.println("2 - Animais de certa dieta.");
            System.out.println("3 - Animais de certo tipo.");
            System.out.println("4 - Maiores do que certa idade.");
            System.out.println("5 - Voltar para trás.");
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1" -> zoo.printZoo();
                    case "2" -> lista2Requerida(12);
                    case "3" -> lista2Requerida(13);
                    case "4" -> lista2Requerida(14);
                    case "5" -> System.out.println();
                    default -> invalid = InvalidText();
                }
            }
        }

    }

    public static void lista2Requerida(int requerimento) {
        Scanner input = new Scanner(System.in);
        String name;
        int ID;
        int age;
        int peso;
        int experience;


        boolean invalid = true;
        if (requerimento == 1) {
            while (invalid) {
                invalid = false;
                System.out.println(zoo.carne);
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar.");
                System.out.println("2 - Remover.");
                System.out.println("3 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Quanto que deseja adicionar?");
                        zoo.addAlimento(Integer.parseInt(input.nextLine()), "carne");
                        break;
                    case "2":
                        System.out.println("Quanto que deseja remover?");
                        zoo.removeAlimento(Integer.parseInt(input.nextLine()), "carne");
                        break;
                    case "3":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                        break;
                }
            }
        }
        if (requerimento == 2) {
            while (invalid) {
                invalid = false;
                System.out.println(zoo.palha);
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar.");
                System.out.println("2 - Remover.");
                System.out.println("3 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Quanto que deseja adicionar?");
                        zoo.addAlimento(Integer.parseInt(input.nextLine()), "palha");
                        break;
                    case "2":
                        System.out.println("Quanto que deseja remover?");
                        zoo.removeAlimento(Integer.parseInt(input.nextLine()), "palha");
                        break;
                    case "3":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                        break;
                }
            }
        }
        if (requerimento == 3) {
            while (invalid) {
                invalid = false;
                System.out.println(zoo.peixe);
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar.");
                System.out.println("2 - Remover.");
                System.out.println("3 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Quanto que deseja adicionar?");
                        zoo.addAlimento(Integer.parseInt(input.nextLine()), "peixe");
                        break;
                    case "2":
                        System.out.println("Quanto que deseja remover?");
                        zoo.removeAlimento(Integer.parseInt(input.nextLine()), "peixe");
                        break;
                    case "3":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                        break;
                }
            }
        }
        if (requerimento == 4) {
            while (invalid) {
                invalid = false;
                zoo.printAnimais("Leões");
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar um novo.");
                System.out.println("2 - Remover um existente.");
                System.out.println("3 - Mudar informações existentes.");
                System.out.println("4 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome do novo leão? (sem nomes repetidos)");
                        name = input.nextLine();
                        System.out.println("Qual é a idade do novo leão?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o peso do novo leão?");
                        peso = Integer.parseInt(input.nextLine());
                        if (!zoo.validateAnimal(name) && age >= 0 && peso >= 0) zoo.addAnimais(name, age, peso, "leão");
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o nome do leão que deseja remover?");
                        name = input.nextLine();
                        if (zoo.validateAnimal(name)) zoo.deleteAnimal(name);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println("Qual é o nome do leão que deseja mudar as informações?");
                        name = input.nextLine();
                        System.out.println("Qual é a atual idade do leão?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o atual peso do leão?");
                        peso = Integer.parseInt(input.nextLine());
                        if (zoo.validateAnimal(name) && age >= 0 && peso >= 0) zoo.changeAnimal(name, age, peso);
                        else invalid = true;
                        break;
                    case "4":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }
        if (requerimento == 5) {
            while (invalid) {
                invalid = false;
                zoo.printAnimais("Elefantes");
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar um novo.");
                System.out.println("2 - Remover um existente.");
                System.out.println("3 - Mudar informações existentes.");
                System.out.println("4 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome do novo elefante? (sem nomes repetidos)");
                        name = input.nextLine();
                        System.out.println("Qual é a idade do novo elefante?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o peso do novo elefante?");
                        peso = Integer.parseInt(input.nextLine());
                        if (!zoo.validateAnimal(name) && age >= 0 && peso >= 0)
                            zoo.addAnimais(name, age, peso, "elefante");
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o nome do elefante que deseja remover?");
                        name = input.nextLine();
                        if (zoo.validateAnimal(name)) zoo.deleteAnimal(name);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println("Qual é o nome do elefante que deseja mudar as informações?");
                        name = input.nextLine();
                        System.out.println("Qual é a atual idade do elefante?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o atual peso do elefante?");
                        peso = Integer.parseInt(input.nextLine());
                        if (zoo.validateAnimal(name) && age >= 0 && peso >= 0) zoo.changeAnimal(name, age, peso);
                        else invalid = InvalidText();
                        break;
                    case "4":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }
        if (requerimento == 6) {
            while (invalid) {
                invalid = false;
                zoo.printAnimais("Girafas");
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar uma nova.");
                System.out.println("2 - Remover uma existente.");
                System.out.println("3 - Mudar informações existentes.");
                System.out.println("4 - Voltar para trás.");

                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome da nova girafa? (sem nomes repetidos)");
                        name = input.nextLine();
                        System.out.println("Qual é a idade da nova girafa?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o peso da nova girafa?");
                        peso = Integer.parseInt(input.nextLine());
                        if (!zoo.validateAnimal(name) && age >= 0 && peso >= 0)
                            zoo.addAnimais(name, age, peso, "girafa");
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o nome da girafa que deseja remover?");
                        name = input.nextLine();
                        if (zoo.validateAnimal(name)) zoo.deleteAnimal(name);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println("Qual é o nome da girafa que deseja mudar as informações?");
                        name = input.nextLine();
                        System.out.println("Qual é a atual idade da girafa?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o atual peso da girafa?");
                        peso = Integer.parseInt(input.nextLine());
                        if (zoo.validateAnimal(name) && age >= 0 && peso >= 0) zoo.changeAnimal(name, age, peso);
                        else invalid = InvalidText();
                        break;
                    case "4":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }
        if (requerimento == 7) {
            while (invalid) {
                invalid = false;
                zoo.printAnimais("Pinguins");
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar um novo.");
                System.out.println("2 - Remover um existente.");
                System.out.println("3 - Mudar informações existentes.");
                System.out.println("4 - Voltar para trás.");

                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome do novo pinguim? (sem nomes repetidos)");
                        name = input.nextLine();
                        System.out.println("Qual é a idade do novo pinguim?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o peso do novo pinguim?");
                        peso = Integer.parseInt(input.nextLine());
                        if (!zoo.validateAnimal(name) && age >= 0 && peso >= 0)
                            zoo.addAnimais(name, age, peso, "pinguim");
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o nome do pinguim que deseja remover?");
                        name = input.nextLine();
                        if (zoo.validateAnimal(name)) zoo.deleteAnimal(name);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println("Qual é o nome do pinguim que deseja mudar as informações?");
                        name = input.nextLine();
                        System.out.println("Qual é a atual idade do pinguim?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é o atual peso do pinguim?");
                        peso = Integer.parseInt(input.nextLine());
                        if (zoo.validateAnimal(name) && age >= 0 && peso >= 0) zoo.changeAnimal(name, age, peso);
                        else invalid = InvalidText();
                        break;
                    case "4":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }
        if (requerimento == 8) {
            while (invalid) {
                invalid = false;
                zoo.printFuncionarios("Tratadores");
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar um novo.");
                System.out.println("2 - Remover um existente.");
                System.out.println("3 - Mudar informações existentes.");
                System.out.println("4 - Associar animal.");
                System.out.println("5 - Dessassociar animal.");
                System.out.println("6 - Alimentar animais.");
                System.out.println("7 - Voltar para trás.");

                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome do novo tratador?");
                        name = input.nextLine();
                        System.out.println("Qual é o ID do novo tratador? (sem IDs repetidos)");
                        ID = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é a idade do novo tratador?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Quantos anos de experiência tem o novo tratador?");
                        experience = Integer.parseInt(input.nextLine());
                        if (!zoo.validateID(ID) && age >= 0 && experience >= 0)
                            zoo.addFuncionarios(name, ID, age, experience, "tratador");
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o ID do tratador que deseja remover?");
                        ID = Integer.parseInt(input.nextLine());
                        if (zoo.validateID(ID)) zoo.deleteFuncionario(ID);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println("Qual é o ID do tratador que deseja mudar as informações?");
                        ID = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é a idade do tratador?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Quantos anos de experiência tem o tratador?");
                        experience = Integer.parseInt(input.nextLine());
                        if (zoo.validateID(ID) && age >= 0 && experience >= 0)
                            zoo.changeFuncionarios(ID, age, experience);
                        else invalid = InvalidText();
                        break;
                    case "4":
                        lista3Requerida(1);
                        break;
                    case "5":
                        lista3Requerida(2);
                        break;
                    case "6":
                        lista3Requerida(3);
                    case "7":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }
        if (requerimento == 9) {
            while (invalid) {
                invalid = false;
                zoo.printFuncionarios("Guias");
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar um novo.");
                System.out.println("2 - Remover um existente.");
                System.out.println("3 - Mudar informações existentes.");
                System.out.println("4 - Voltar para trás.");

                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome do novo guia?");
                        name = input.nextLine();
                        System.out.println("Qual é o ID do novo guia? (sem IDs repetidos)");
                        ID = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é a idade do novo guia?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Quantos anos de experiência tem o novo guia?");
                        experience = Integer.parseInt(input.nextLine());
                        if (!zoo.validateID(ID) && age >= 0 && experience >= 0)
                            zoo.addFuncionarios(name, ID, age, experience, "guia");
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o ID do guia que deseja remover?");
                        ID = Integer.parseInt(input.nextLine());
                        if (zoo.validateID(ID)) zoo.deleteFuncionario(ID);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println("Qual é o ID do guia que deseja mudar as informações?");
                        ID = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é a idade do guia?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Quantos anos de experiência tem o guia?");
                        experience = Integer.parseInt(input.nextLine());
                        if (zoo.validateID(ID) && age >= 0 && experience >= 0)
                            zoo.changeFuncionarios(ID, age, experience);
                        else invalid = InvalidText();
                        break;
                    case "4":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }
        if (requerimento == 10) {
            while (invalid) {
                invalid = false;
                zoo.printFuncionarios("Administradores");
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar um novo.");
                System.out.println("2 - Remover um existente.");
                System.out.println("3 - Mudar informações existentes.");
                System.out.println("4 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome do novo administrador?");
                        name = input.nextLine();
                        System.out.println("Qual é o ID do novo administrador? (sem IDs repetidos)");
                        ID = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é a idade do novo administrador?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Quantos anos de experiência tem o novo administrador?");
                        experience = Integer.parseInt(input.nextLine());
                        if (!zoo.validateID(ID) && age >= 0 && experience >= 0)
                            zoo.addFuncionarios(name, ID, age, experience, "administrador");
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o ID do administrador que deseja remover?");
                        ID = Integer.parseInt(input.nextLine());
                        if (zoo.validateID(ID)) zoo.deleteFuncionario(ID);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println("Qual é o ID do administrador que deseja mudar as informações?");
                        ID = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é a idade do administrador?");
                        age = Integer.parseInt(input.nextLine());
                        System.out.println("Quantos anos de experiência tem o administrador?");
                        experience = Integer.parseInt(input.nextLine());
                        if (zoo.validateID(ID) && age >= 0 && experience >= 0)
                            zoo.changeFuncionarios(ID, age, experience);
                        else invalid = InvalidText();
                        break;
                    case "4":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }
        if (requerimento == 11) {
            while (invalid) {
                invalid = false;
                zoo.printVisitantes();
                System.out.println("O que pretende fazer?");
                System.out.println("1 - Adicionar um novo.");
                System.out.println("2 - Remover um existente.");
                System.out.println("3 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome do novo visitante?");
                        name = input.nextLine();
                        System.out.println("Qual é o ID do novo visitante? (sem IDs repetidos)");
                        ID = Integer.parseInt(input.nextLine());
                        System.out.println("Qual é a idade do novo visitante?");
                        age = Integer.parseInt(input.nextLine());
                        if (!zoo.validateID(ID) && age >= 0) zoo.addVisitante(name, ID, age);
                        else invalid = InvalidText();
                        break;
                    case "2":
                        System.out.println("Qual é o ID do visitante que deseja remover?");
                        ID = Integer.parseInt(input.nextLine());
                        if (zoo.validateID(ID)) zoo.deleteVisitante(ID);
                        else invalid = InvalidText();
                        break;
                    case "3":
                        System.out.println();
                        break;
                    default:
                        invalid = InvalidText();
                }
            }
        }

        if (requerimento == 12) {
            while (invalid) {
                invalid = false;
                System.out.println("Quais são os animais da deita que deseja visualizar?");
                System.out.println("1 - Carnivoros.");
                System.out.println("2 - Herbivoros.");
                System.out.println("3 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1" -> zoo.printAnimais("Carnivoro");
                    case "2" -> zoo.printAnimais("Herbivoro");
                    case "3" -> System.out.println();
                    default -> invalid = InvalidText();
                }
            }
        }
        if (requerimento == 13) {
            while (invalid) {
                invalid = false;
                System.out.println("Quais são os animais do tipo que deseja visualizar?");
                System.out.println("1 - Mamíferos.");
                System.out.println("2 - Aves.");
                System.out.println("3 - Voltar para trás.");
                switch (input.nextLine()) {
                    case "1" -> zoo.printAnimais("Mamífero");
                    case "2" -> zoo.printAnimais("Aves");
                    case "3" -> System.out.println();
                    default -> invalid = InvalidText();
                }
            }
        }
        if (requerimento == 14) {
            System.out.println("Qual é a idade minima dos animais que deseja visualizar?");
            zoo.printAnimais(Integer.parseInt(input.nextLine()));
        }
    }

    public static void lista3Requerida(int requerimento) {
        Scanner input = new Scanner(System.in);
        String name;
        int ID;
        boolean invalid = true;

        System.out.println();
        zoo.printFuncionarios("Tratadores");
        zoo.printAnimais("Leões");
        zoo.printAnimais("Elefantes");
        zoo.printAnimais("Girafas");
        zoo.printAnimais("Pinguins");
        if (requerimento == 1) {
            while (invalid) {
                invalid = false;
                System.out.println("\nQual é o ID do Tratador a quem deseja associar um animal?");
                ID = Integer.parseInt(input.nextLine());
                System.out.println("Qual é o nome do animal que será associado?");
                name = input.nextLine();
                if (!zoo.validateAnimalInTratador(ID, name)) zoo.setAnimalTratador(ID, name);
                else InvalidText();

            }
        }
        if (requerimento == 2) {
            while (invalid) {
                invalid = false;
                System.out.println("\nQual é o ID do Tratador a quem deseja desassociar um animal?");
                ID = Integer.parseInt(input.nextLine());
                System.out.println("Qual é o nome do animal que será desassociado?");
                name = input.nextLine();
                if (zoo.validateAnimalInTratador(ID, name)) zoo.removeAnimalTratador(ID, name);
                else InvalidText();
            }
        }
        if (requerimento == 3) {
            while (invalid) {
                invalid = false;
                System.out.println("\nQual é o ID do tratador que irá alimentar os animais?");
                ID = Integer.parseInt(input.nextLine());
                System.out.println("Qual é o nome do animal que deseja alimentar? (tem que pertencer ao tratador) ('todos' é uma opção)");
                name = input.nextLine();
                if ((name.equals("todos") || zoo.validateAnimalInTratador(ID, name)) && zoo.requiredFoodToAnimal(ID, name))
                    zoo.darComida(ID, name);
                else invalid = InvalidText();
            }
        }
    }

    public static void salvarZoo() {
        Scanner input = new Scanner(System.in);
        String ficheiro;
        System.out.println("Qual é o nome do atual/novo ficheiro?");
        ficheiro = input.nextLine();
        try (FileOutputStream zooOutputFile = new FileOutputStream(ficheiro + ".sav"); ObjectOutputStream zooOutputObject = new ObjectOutputStream(zooOutputFile)) {
            zooOutputObject.writeObject(zoo);
            zooOutputObject.flush();
            zooOutputObject.close();
            System.out.println("Zoo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar zoo!");
        }
    }

    public static void salvarZoo(String ficheiro) {
        try (FileOutputStream zooOutputFile = new FileOutputStream(ficheiro + ".sav"); ObjectOutputStream zooOutputObject = new ObjectOutputStream(zooOutputFile)) {
            zooOutputObject.writeObject(zoo);
            zooOutputObject.flush();
            zooOutputObject.close();
            System.out.println("Zoo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar zoo!");
        }
    }


    private static boolean carregarZoo(String nomeDoFicheiro) throws ClassNotFoundException {
        ficheiro = nomeDoFicheiro;
        try (FileInputStream zooInputFile = new FileInputStream(ficheiro + ".sav"); ObjectInputStream zooInputObject = new ObjectInputStream(zooInputFile)) {
            zoo = (Zoo) zooInputObject.readObject();
        } catch (IOException e) {
            System.out.println("Erro ao carregar zoo!");
            return true;
        }
        return false;
    }

    private static void encerrarPrograma() {
        System.out.println("Este é o final do seu zoo:");
        salvarZoo(ficheiro);
        zoo.printZoo();
        System.exit(0);
    }

    private static boolean InvalidText() {
        System.out.println("A opção ou um dos argumentos não é válido.");
        return true;
    }
}