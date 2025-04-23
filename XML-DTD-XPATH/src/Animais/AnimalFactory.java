package Animais;

public class AnimalFactory {
    public AnimalFactory() {
    }

    public static Animal getAnimal(String name, int age, int weight, String type) {
        switch (type) {
            case "leão" -> {
                return new Leao(name, age, weight, "Carnivoro", "Mamífero");
            }
            case "elefante" -> {
                return new Elefante(name, age, weight, "Herbivoro", "Mamífero");
            }
            case "girafa" -> {
                return new Girafa(name, age, weight, "Herbivoro", "Mamífero");
            }
            case "pinguim" -> {
                return new Pinguim(name, age, weight, "Carnivoro", "Ave");
            }
            default -> {
                return null;
            }
        }
    }
}
