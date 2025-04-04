package app.repository;

import app.domain.Owner;
import app.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetRepository {

    private final File database;

    private final ObjectMapper mapper;

    private int maxId;

    public PetRepository() throws IOException {
        database = new File("database/pet.txt");
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Pet> pets = findAll();

        if (!pets.isEmpty()) {
            Pet lastPet = pets.get(pets.size() - 1);
            maxId = lastPet.getId();
        }
    }

    public Pet save(Pet pet) throws IOException {
        pet.setId(++maxId);
        List<Pet> pets = findAll();
        pets.add(pet);
        mapper.writeValue(database, pets);
        return pet;
    }

    public List<Pet> findAll() throws IOException {
        try {
            Pet[] pets = mapper.readValue(database, Pet[].class);
            return new ArrayList<>(Arrays.asList(pets));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    public Pet findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Pet pet) throws IOException {
        int id = pet.getId();
        int newAge = pet.getAge();
        boolean active = pet.isActive();


        List<Pet> pets = findAll();
        pets
                .stream()
                .filter(x -> x.getId() == id)
                .forEach(x -> {
                    x.setActive(active);
                    x.setAge(newAge);

                });
        mapper.writeValue(database, pets);
    }

    public void deleteById(int id) throws IOException {
        List<Pet> pets = findAll();
        pets.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, pets);
    }
}
