package Funcionarios;

public class EmployeeFactory {
    public EmployeeFactory() {
    }

    public static Funcionario getFuncionario(String name, int age, int ID, int experience, String type) {
        switch (type) {
            case "tratador" -> {
                return new Tratador(name, age, ID, experience);
            }
            case "administrador" -> {
                return new Administrador(name, age, ID, experience);
            }
            case "guia" -> {
                return new Guia(name, age, ID, experience);
            }
            default -> {
                return null;
            }
        }
    }
}
