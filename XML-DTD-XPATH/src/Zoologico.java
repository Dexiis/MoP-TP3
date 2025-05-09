import java.util.Scanner;

public class Zoologico {
    static Zoo zoo;
    static String ficheiro = "BaseDados";

    public static void main(String[] args) {
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
                        Object[] result = carregarZoo("ZooPredefinido");
                        zoo = (Zoo) result[0];
                        invalid = (Boolean) result[1];
                        break;
                    }
                    System.out.println("Dê um nome para o seu zoo: ");
                    String zooName = input.nextLine();
                    System.out.println("Qual será o preçario para o seu zoo:");
                    int zooPrecario = Integer.parseInt(input.nextLine());
                    zoo = Zoo.getZooInstance(zooName, zooPrecario);
                    invalid = false;
                    break;
                case "2":
                    System.out.println("Qual é o nome do ficheiro que contem o seu zoo?");
                    Object[] result = carregarZoo(input.nextLine());
                    zoo = (Zoo) result[0];
                    invalid = (Boolean) result[1];
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
            System.out.println("4 - Ver Zoológico.");
            System.out.println("5 - Ver outros zoológicos.");
            System.out.println("6 - Salvar o zoo.");
            System.out.println("7 - Terminar todas as operações e salvar o zoo.");

            switch (input.nextLine()) {
                case "1" -> listaRequerida(1);
                case "2" -> listaRequerida(2);
                case "3" -> listaRequerida(3);
                case "4" -> listaRequerida(4);
                case "5" -> otherZooPOV();
                case "6" -> salvarZoo();
                case "7" -> encerrarPrograma();
                default -> System.out.println("Essa opção não é válida.");
            }
        }
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
                System.out.println("1 - Carnívoros.");
                System.out.println("2 - Herbívoros.");
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

    private static void otherZooPOV() {
        Scanner input = new Scanner(System.in);
        boolean mode = true;
        System.out.println("Escreva o nome do ficheiro que deseja visualizar.");
        String otherZooFile = input.nextLine();
        if (otherZooFile.equals(ficheiro)) {
            System.out.println("Esse é o mesmo ficheiro que tem em aberto!");
            mode = false;
        }
        while (mode) {
            System.out.println("Escolha uma opção.");
            System.out.println("1 - Ver o Zoo inteiro.");
            System.out.println("2 - Procurar por entidades.");
            System.out.println("3 - Procurar por caracteristica nas entidades.");
            System.out.println("4 - Procurar pela quantidade da entidade.");
            System.out.println("5 - Voltar ao Zoo principal.");
            switch (input.nextLine()) {
                case "1" -> XPATH.printWholeZoo(otherZooFile);
                case "2" -> OZPRequiredList(otherZooFile, 1);
                case "3" -> OZPRequiredList(otherZooFile, 2);
                case "4" -> OZPRequiredList(otherZooFile, 3);
                case "5" -> mode = false;
                default -> System.out.println("Essa opção não foi encontrada.");
            }
        }
    }

    private static void OZPRequiredList(String otherZooFile, int requirement) {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        if (requirement == 1) {
            printOutputEntities();
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1" -> XPATH.lookingForEntityList("leao", otherZooFile);
                    case "2" -> XPATH.lookingForEntityList("elefante", otherZooFile);
                    case "3" -> XPATH.lookingForEntityList("girafa", otherZooFile);
                    case "4" -> XPATH.lookingForEntityList("pinguim", otherZooFile);
                    case "5" -> XPATH.lookingForEntityList("tratador", otherZooFile);
                    case "6" -> XPATH.lookingForEntityList("administrador", otherZooFile);
                    case "7" -> XPATH.lookingForEntityList("guia", otherZooFile);
                    case "8" -> XPATH.lookingForEntityList("visitante", otherZooFile);
                    case "9" -> XPATH.lookingForEntityList("animais", otherZooFile);
                    case "10" -> XPATH.lookingForEntityList("funcionarios", otherZooFile);
                    default -> invalid = InvalidText();
                }
            }
        }

        if (requirement == 2) {
            printOutputEntities();
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1" -> OZPRequiredList2("leao", otherZooFile);
                    case "2" -> OZPRequiredList2("elefante", otherZooFile);
                    case "3" -> OZPRequiredList2("girafa", otherZooFile);
                    case "4" -> OZPRequiredList2("pinguim", otherZooFile);
                    case "5" -> OZPRequiredList2("tratador", otherZooFile);
                    case "6" -> OZPRequiredList2("administrador", otherZooFile);
                    case "7" -> OZPRequiredList2("guia", otherZooFile);
                    case "8" -> OZPRequiredList2("visitante", otherZooFile);
                    case "9" -> OZPRequiredList2("animais", otherZooFile);
                    case "10" -> OZPRequiredList2("funcionarios", otherZooFile);
                    default -> System.out.println("Essa opção não foi encontrada.");
                }
            }
        }

        if (requirement == 3) {
            printOutputEntities();
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1" -> XPATH.lookingForNumber("leao", otherZooFile);
                    case "2" -> XPATH.lookingForNumber("elefante", otherZooFile);
                    case "3" -> XPATH.lookingForNumber("girafa", otherZooFile);
                    case "4" -> XPATH.lookingForNumber("pinguim", otherZooFile);
                    case "5" -> XPATH.lookingForNumber("tratador", otherZooFile);
                    case "6" -> XPATH.lookingForNumber("administrador", otherZooFile);
                    case "7" -> XPATH.lookingForNumber("guia", otherZooFile);
                    case "8" -> XPATH.lookingForNumber("visitante", otherZooFile);
                    case "9" -> XPATH.lookingForNumber("animais", otherZooFile);
                    case "10" -> XPATH.lookingForNumber("funcionarios", otherZooFile);
                    default -> System.out.println("Essa opção não foi encontrada.");
                }
            }
        }
    }

    private static void OZPRequiredList2(String entity, String otherZooFile) {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        if (entity.equals("leao") | entity.equals("elefante") | entity.equals("girafa") | entity.equals("pinguim") | entity.equals("animais")) {
            System.out.println("Qual é a caracteristica que deseja procurar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Idade");
            System.out.println("3 - Peso");
            System.out.println("4 - Dieta");
            System.out.println("5 - Tipo");
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "name", input.nextLine(), otherZooFile);
                        break;
                    case "2":
                        System.out.println("Qual é a idade que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "age", input.nextLine(), otherZooFile);
                        break;
                    case "3":
                        System.out.println("Qual é o peso que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "weight", input.nextLine(), otherZooFile);
                        break;
                    case "4":
                        System.out.println("Qual é a deita que deseja procurar?\n (Carnivoro ou Herbivero)");
                        XPATH.lookingForCharacteristic(entity, "diet", input.nextLine(), otherZooFile);
                        break;
                    case "5":
                        System.out.println("Qual é o tipo que deseja procurar? (Mamífero ou Ave)");
                        XPATH.lookingForCharacteristic(entity, "type", input.nextLine(), otherZooFile);
                        break;
                    default:
                        System.out.println("Essa opção não foi encontrada.");
                        break;
                }
            }
        }

        if (entity.equals("tratador") | entity.equals("guia") | entity.equals("administrador") | entity.equals("funcionarios")) {
            System.out.println("Qual é a caracteristica que deseja procurar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Idade");
            System.out.println("3 - ID");
            System.out.println("4 - Experiencia");
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "name", input.nextLine(), otherZooFile);
                        break;
                    case "2":
                        System.out.println("Qual é a idade que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "age", input.nextLine(), otherZooFile);
                        break;
                    case "3":
                        System.out.println("Qual é o peso que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "ID", input.nextLine(), otherZooFile);
                        break;
                    case "4":
                        System.out.println("Qual é a deita que deseja procurar?\n (Carnivoro ou Herbivero)");
                        XPATH.lookingForCharacteristic(entity, "experience", input.nextLine(), otherZooFile);
                        break;
                    default:
                        System.out.println("Essa opção não foi encontrada.");
                        break;
                }
            }
        }

        if (entity.equals("visitante")) {
            System.out.println("Qual é a caracteristica que deseja procurar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Idade");
            System.out.println("3 - ID");
            while (invalid) {
                invalid = false;
                switch (input.nextLine()) {
                    case "1":
                        System.out.println("Qual é o nome que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "name", input.nextLine(), otherZooFile);
                        break;
                    case "2":
                        System.out.println("Qual é a idade que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "age", input.nextLine(), otherZooFile);
                        break;
                    case "3":
                        System.out.println("Qual é o peso que deseja procurar?");
                        XPATH.lookingForCharacteristic(entity, "ID", input.nextLine(), otherZooFile);
                        break;
                    default:
                        System.out.println("Essa opção não foi encontrada.");
                        break;
                }
            }
        }
    }

    private static void printOutputEntities() {
        System.out.println("Qual é a entidade que deseja procurar?");
        System.out.println("1 - Leões");
        System.out.println("2 - Elefantes");
        System.out.println("3 - Girafas");
        System.out.println("4 - Pinguins");
        System.out.println("5 - Tratadores");
        System.out.println("6 - Administradores");
        System.out.println("7 - Guias");
        System.out.println("8 - Visitantes");
        System.out.println("9 - Animais");
        System.out.println("10 - Funcionarios");
    }

    /**
     * Solicita ao utilizador o nome do ficheiro para guardar o estado atual do zoo e depois chama o método para guardar o zoo em formato XML.
     */
    private static void salvarZoo() {
        Scanner input = new Scanner(System.in);
        System.out.println("Qual é o nome do ficheiro que quer guardar o Zoo?");
        ficheiro = input.nextLine();
        XML.salvarZoo(zoo.name, zoo.precario, zoo.animais, zoo.funcionarios, zoo.visitantes, zoo.carne, zoo.palha, zoo.peixe, ficheiro);
    }

    /**
     * Define o nome do ficheiro global e chama o método da classe XML para carregar o Zoo a partir de um ficheiro XML.
     *
     * @param nomeDoFicheiro O nome do ficheiro XML (.xml) a partir do qual carregar o Zoo.
     * @return Um array de 'Object' contendo a instância carregada do Zoo (índice 0) e um booleano indicando se ocorreu um erro durante o carregamento (índice 1).
     */
    private static Object[] carregarZoo(String nomeDoFicheiro) {
        ficheiro = nomeDoFicheiro;
        return XML.carregarZoo(ficheiro);
    }

    /**
     * Encerra o programa, guardando o estado atual do zoo para um ficheiro XML, imprimindo os detalhes finais do zoo e saindo da aplicação.
     */
    private static void encerrarPrograma() {
        System.out.println("Este é o final do seu zoo:");
        XML.salvarZoo(zoo.name, zoo.precario, zoo.animais, zoo.funcionarios, zoo.visitantes, zoo.carne, zoo.palha, zoo.peixe, ficheiro);
        zoo.printZoo();
        System.exit(0);
    }

    /**
     * Imprime uma mensagem de erro indicando que a opção ou os argumentos fornecidos são inválidos.
     *
     * @return Retorna sempre {@code true}.
     */
    private static boolean InvalidText() {
        System.out.println("A opção ou um dos argumentos não é válido.");
        return true;
    }
}