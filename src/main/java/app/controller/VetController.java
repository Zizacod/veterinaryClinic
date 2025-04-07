package app.controller;

import app.domain.Vet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.petExceptions.PetNotFoundException;
import app.exception.vetExceptions.VetNotFoundException;
import app.exception.vetExceptions.VetSaveException;
import app.exception.vetExceptions.VetUpdateException;
import app.service.VetService;

import java.io.IOException;
import java.util.List;

public class VetController {

    private final VetService service;

    public VetController() throws IOException, OwnerNotFoundException, PetNotFoundException {
        service = new VetService();
    }

    //    1.сохранить нового ветеринара в базе

    public Vet save(String name, String role) throws VetSaveException, IOException {
        Vet vet = new Vet(name, role);
        return service.save(vet);
    }
//

    /// /    2.вернуть всех активных ветеринаров

    public List<Vet> getAllActiveVets() throws IOException {
        return service.getAllActiveVets();
    }
//

    /// /    3.вернуть ветеринара по id (если он активен)

    public Vet getActiveVetById(int id) throws VetNotFoundException, IOException {
        return service.getActiveVetById(id);
    }

//

    /// /    4.Изменить данные ветеринара в базе данных по его id
//
    public void update(int id, String name, String role) throws IOException, VetUpdateException {

        Vet vet = new Vet(id, name, role);
        service.update(vet);
    }
//

    /// /    5.Удалить ветеринара из базы данных по его id
//
    public void deleteById(int id) throws VetNotFoundException, IOException {
        service.deleteById(id);
    }

//

    /// /    6.Удалить ветеринара из базы данных по его имени
//
    public void deleteByName(String name) throws VetNotFoundException, IOException {
        service.deleteByName(name);
    }
//

    /// /    7.Восстановить удаленного ветеринара по его id
//
    public void restoreById(int id) throws VetNotFoundException, IOException {
        service.restoreById(id);
    }

//

    /// /    8.Вернуть общее количество ветеринаров в базе данных (только активных)
//
    public int getActiveVetsNumber() throws IOException {
        return service.getActiveVetsNumber();
    }
//

    /// /    9.Показать всех ветеринаров раньше работающих в клинике
//
    public List<Vet> getAllInactiveVets() throws IOException {
        return service.getAllInactiveVets();
    }


//
////    10.Найти веринаров по конкретной должности (передаем String role)
//
    public List<Vet> getVetsByRole(String role) throws IOException {
        return service.getAllVetByRole(role);
    }

//
////    12.вернуть все визиты, которые проводил конкретный ветеринар
//
//    // может это прописать в визитах?
//
////    13.проверить активность ветеринара по его id, может ли он принимать или уволен
//
//    // за каким параметром это проверять, мне кажеться можем этот метод удалить

}
