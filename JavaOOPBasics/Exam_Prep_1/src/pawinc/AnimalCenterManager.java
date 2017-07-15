package pawinc;

import pawinc.models.animals.Animal;
import pawinc.models.animals.Cat;
import pawinc.models.animals.Dog;
import pawinc.models.centers.AdoptionCenter;
import pawinc.models.centers.CastrationCenter;
import pawinc.models.centers.CleansingCenter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalCenterManager {

    private HashMap<String, AdoptionCenter> adoptionCenters;
    private HashMap<String, CleansingCenter>cleansingCenters;
    private HashMap<String, CastrationCenter>castrationCenters;
    private List<Animal> cleansedAnimals;
    private List<Animal> adoptedAnimals;
    private List<Animal> castratedAnimals;

    public AnimalCenterManager() {
        adoptionCenters = new HashMap<>();
        cleansingCenters = new HashMap<>();
        castrationCenters = new HashMap<>();
        this.cleansedAnimals = new ArrayList<>();
        this.adoptedAnimals = new ArrayList<>();
        castratedAnimals = new ArrayList<>();
    }

    public void registerCleansingCenter(String name) {
        CleansingCenter cleansingCenter = new CleansingCenter(name);
        cleansingCenters.put(name, cleansingCenter);;
    }
    public void registerAdoptionCenter(String name) {
        AdoptionCenter adoptionCenter = new AdoptionCenter(name);
        adoptionCenters.put(name, adoptionCenter);
    }

    public void registerCastrationCenter(String name) {
        CastrationCenter castrationCenter = new CastrationCenter(name);
        castrationCenters.put(name, castrationCenter);
    }

    public void registerDog(String name, int age, int learnedCommands, String
            adoptionCenterName) {
        Dog dog = new Dog(name, age, learnedCommands, adoptionCenterName);
        this.adoptionCenters.get(adoptionCenterName).register(dog);
    }
    public void registerCat(String name, int age, int intelligenceCoefficient, String
            adoptionCenterName) {
        Cat cat = new Cat(name, age, intelligenceCoefficient, adoptionCenterName);
        this.adoptionCenters.get(adoptionCenterName).register(cat);
    }
    public void sendForCleansing(String adoptionCenterName, String cleansingCenterName) {
        CleansingCenter cleansingCenter = this.cleansingCenters.get(cleansingCenterName);
        this.adoptionCenters.get(adoptionCenterName).sendForCleansing(cleansingCenter);
    }

    public void sendForCastration(String adoptionCenterName, String castrationCenterName) {
        CastrationCenter castrationCenter = this.castrationCenters.get(castrationCenterName);
        this.adoptionCenters.get(adoptionCenterName).sendForCastration(castrationCenter);
    }

    public void cleanse(String cleansingCenterName) {
        CleansingCenter cleansingCenter = this.cleansingCenters.get(cleansingCenterName);
        List<Animal> animals = cleansingCenter.cleanse();
        for (Animal animal : animals) {
            this.adoptionCenters.get(animal.getCenterName()).register(animal);
        }

        this.cleansedAnimals.addAll(animals);
    }
    public void adopt(String adoptionCenterName) {
        List<Animal> adoptedAnimals = this.adoptionCenters.get(adoptionCenterName).adopt();

        this.adoptedAnimals.addAll(adoptedAnimals);
    }

    public void castrate(String castrationCenterName) {
        List<Animal> animals = this.castrationCenters.get(castrationCenterName).castrate();

        for (Animal animal : animals) {
            this.adoptionCenters.get(animal.getCenterName()).register(animal);
        }

        this.castratedAnimals.addAll(animals);

    }
    public void printStatistics() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Paw Incorporative Regular Statistics\n");
        stringBuilder.append(String.format("Adoption Centers: %d\n", this.adoptionCenters.size()));
        stringBuilder.append(String.format("Cleansing Centers: %d\n", this.cleansingCenters.size()));
        stringBuilder.append(String.format("Adopted Animals: %s\n", this.getAnimals(this.adoptedAnimals)));
        stringBuilder.append(String.format("Cleansed Animals: %s\n", this.getAnimals(this.cleansedAnimals)));
        stringBuilder.append(String.format("Animals Awaiting Adoption: %d\n", getAwaitingCountAdoption()));
        stringBuilder.append(String.format("Animals Awaiting Cleansing: %d", getAwaitingCountCleansed()));

        System.out.println(stringBuilder.toString());
    }

    public void printCastrationStatistics() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Paw Inc. Regular Castration Statistics\n");
        stringBuilder.append(String.format("Castration Centers: %d\n", this.castrationCenters.size()));
        stringBuilder.append(String.format("Castrated Animals: %s", this.getAnimals(this.castratedAnimals)));

        System.out.println(stringBuilder.toString());
    }

    private int getAwaitingCountCleansed() {
        return this.cleansingCenters.values().stream()
                .flatMap(a -> a.getAnimals()
                        .stream())
                .collect(Collectors.
                        toList())
                .size();
    }

    private int getAwaitingCountAdoption() {
       return this.adoptionCenters.values().stream()
               .flatMap(a -> a.getAnimals()
                       .stream())
               .filter(a -> a.isCleansed().equals("CLEANSED"))
               .collect(Collectors.
                       toList())
               .size();
    }

    private String getAnimals(List<Animal> animals) {
        if(animals.isEmpty()) {
            return "None";
        }

        List<String> sorted = animals.stream().map(a -> a.getName()).sorted(Collator.getInstance()).collect(Collectors.toList());

        return String.join(", ", sorted);
    }

}
