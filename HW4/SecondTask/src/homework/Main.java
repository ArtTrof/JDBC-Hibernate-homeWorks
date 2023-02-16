package homework;

import homework.entity.Animal;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnimalHelper animalHelper = new AnimalHelper();
        Animal animal = new Animal();
        animal.setId(2);
        animal.setName("Dawg");
        animal.setTail(true);
        animalHelper.addAnimal(animal);
        animalHelper.getAnimalByID(1);
        List<Animal> animals= animalHelper.getAnimals();
    }
}
