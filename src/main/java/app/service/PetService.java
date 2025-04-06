package app.service;

import app.domain.Owner;
import app.domain.Pet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.petExceptions.PetNotFoundException;
import app.exception.petExceptions.PetSaveException;
import app.exception.petExceptions.PetUpdateException;
import app.repository.PetRepository;

import java.io.IOException;

import java.util.List;

public class PetService {

    private OwnerService ownerService;
    private VetService vetService;

    private final PetRepository repository;


    public PetService() throws IOException, OwnerNotFoundException, PetNotFoundException {
        repository = new PetRepository();
    }

    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public void setVetService(VetService vetService) {
        this.vetService = vetService;
    }

    //1. Сохранить животное в базе данных
    public Pet save(Pet pet) throws PetSaveException, IOException {
        if (pet == null) {
            throw new PetSaveException("Питомец не может быть null!");
        }
        String name = pet.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new PetSaveException("Имя питомца не может быть пустым!");
        }

        String breed = pet.getBreed();
        if (breed == null || breed.trim().isEmpty()) {
            throw new PetSaveException("Порода питомца не может быть null!");
        }

        String color = pet.getColor();
        if (color == null || color.trim().isEmpty()) {
            throw new PetSaveException("Окрас питомца не может быть null!");
        }

        int age = pet.getAge();
        if (age < 0) {
            throw new PetSaveException("Возраст питомца не может быть отрицательным!");
        }

        pet.setActive(true);
        return repository.save(pet);
    }

//     2.Вернуть всех (активных) животных из базы данных

    public List<Pet> getAllActivePets() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Pet::isActive)
                .toList();
    }

//     3.Вернуть одного питомца по его id (если он активен)

    public Pet getActivePetById(int id) throws PetNotFoundException, IOException {
        Pet pet = repository.findById(id);

        if (pet == null || !pet.isActive()) {
            throw new PetNotFoundException(id);
        }
        return pet;
    }

//     4.Изменить данные питомца в базе данных по его id

    public void update(Pet pet) throws PetUpdateException, IOException {
        if (pet == null) {
            throw new PetUpdateException("Питомец не может быть null!");
        }
        int age = pet.getAge();

        if (age < 0) {
            throw new PetUpdateException("Возраст питомца не может быть отрицательным!");
        }
        pet.setActive(true);
        repository.update(pet);
    }

//     5.Удалить питомца из базы данных по его id

    public void deleteById(int id) throws IOException, PetNotFoundException {
        Pet pet = getActivePetById(id);
        pet.setActive(false);
        repository.update(pet);
    }

//     6.Восстановить удаленного питомца по его id

    public void restoreById(int id) throws IOException, PetNotFoundException {
        Pet pet = repository.findById(id);

        if (pet != null) {
            pet.setActive(true);
            repository.update(pet);
        } else {
            throw new PetNotFoundException(id);
        }
    }

//     7.Вернуть общее число питомцев в базе данных (только активных)

    public int getActivePetsNumber() throws IOException {
        return getAllActivePets().size();
    }


//     9.Получить историю всех визитов конкретного питомца

    // после создания визитСервиса

//     10.Получить список всех врачей, лечивших данного питомца

    // после создания сервиса врачей

//     11.Удалить все визиты питомца по id питомца

    // после создания визитСервиса
}
