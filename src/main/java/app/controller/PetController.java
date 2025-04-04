package app.controller;

import app.domain.Pet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.petExceptions.PetNotFoundException;
import app.exception.petExceptions.PetSaveException;
import app.exception.petExceptions.PetUpdateException;
import app.service.PetService;

import java.io.IOException;
import java.util.List;

public class PetController {

    private final PetService service;

    public PetController() throws IOException, OwnerNotFoundException, PetNotFoundException {
        service = new PetService();

    }

    //1. Сохранить животное в базе данных
    public Pet save(String name, String breed, String color, int age) throws PetSaveException, IOException {
        Pet pet = new Pet(name, breed, color, age);
        return service.save(pet);
    }

//     2.Вернуть всех (активных) животных из базы данных

    public List<Pet> getAllActivePets() throws IOException {
        return service.getAllActivePets();
    }

//     3.Вернуть одного питомца по его id (если он активен)

    public Pet getActivePetById(int id) throws IOException, PetNotFoundException {
        return service.getActivePetById(id);
    }

//     4.Изменить данные питомца в базе данных по его id

    public void update(int id, int age) throws IOException, PetNotFoundException, PetUpdateException {
        Pet pet = new Pet(id, age);
        service.update(pet);
    }

//     5.Удалить питомца из базы данных по его id

    public void deleteById(int id) throws IOException, PetNotFoundException {
        service.deleteById(id);
    }

//     6.Восстановить удаленного питомца по его id

    public void restoreById(int id) throws IOException, PetNotFoundException {
        service.restoreById(id);
    }

    //    7.Вернуть общее число питомцев в базе данных (только активных)

    public int getActivePetsNumber() throws IOException {
        return service.getActivePetsNumber();
    }

//     8.Вернуть всех питомцев конкретного владельца по id владельца

//        public List<Pet> getPetsByOwnerId ( int ownerId){
//
//    }

//     9.Получить историю всех визитов конкретного питомца

    // после создания визитСервиса

//     10.Получить список всех врачей, лечивших данного питомца

    // после создания сервиса врачей

//     11.Удалить все визиты питомца по id питомца

    // после создания визитСервиса
}

