package app.service;

import app.domain.Owner;
import app.domain.Pet;
import app.domain.Vet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.ownerExceptions.OwnerSaveException;
import app.exception.ownerExceptions.OwnerUpdateException;
import app.exception.petExceptions.PetNotFoundException;
import app.exception.vetExceptions.VetNotFoundException;
import app.exception.vetExceptions.VetSaveException;
import app.exception.vetExceptions.VetUpdateException;
import app.repository.VetRepository;

import java.io.IOException;
import java.util.List;

public class VetService {

    private final VetRepository repository;
    private PetService petService;

    public VetService() throws IOException {
        repository = new VetRepository();
    }

    public void setPetService(PetService petService) {
        this.petService = petService;
    }

    //    1. сохранить нового ветеринара в базе
    public Vet save(Vet vet) throws IOException, VetSaveException {
        if (vet == null) {
            throw new VetSaveException("Ветеринар не может быть null!");
        }

        String name = vet.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new VetSaveException("Имя ветеринара не может быть null!");
        }

        String role = vet.getRole();

        if (role == null || role.trim().isEmpty()) {
            throw new VetSaveException("Должность ветеринара не может быть null!");
        }

        vet.setActive(true);
        return repository.save(vet);
    }
//2. вернуть всех активных ветеринаров

    public List<Vet> getAllActiveVets() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Vet::isActive)
                .toList();
    }

    //3. вернуть ветеринара по id (если он активен)
    public Vet getActiveVetById(int id) throws IOException, VetNotFoundException {
        Vet vet = repository.findById(id);

        if (vet == null || !vet.isActive()) {
            throw new VetNotFoundException(id);
        }
        return vet;
    }

    //4. Изменить данные ветеринара в базе данных по его id
    public void update(Vet vet) throws IOException, VetUpdateException {
        if (vet == null) {
            throw new VetUpdateException("Ветеринар не может быть null!");
        }
        String name = vet.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new VetUpdateException("Имя ветеринара не может быть null!");
        }

        String role = vet.getRole();

        if (role == null || role.trim().isEmpty()) {
            throw new VetUpdateException("Должность ветеринара не может быть null!");
        }

        vet.setActive(true);
        repository.update(vet);
    }
//5. Удалить ветеринара из базы данных по его id

    public void deleteById(int id) throws IOException, VetNotFoundException {
        Vet vet = getActiveVetById(id);
        vet.setActive(false);
        repository.update(vet);
    }

    //6. Удалить ветеринара по имени
    public void deleteByName(String name) throws IOException, VetNotFoundException {
        Vet vet = getAllActiveVets()
                .stream()
                .filter(x -> x.getName().equals(name))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(() -> new VetNotFoundException(name));
        repository.update(vet);
    }

    //7. Восстановить удаленного ветеринара по его id
    public void restoreById(int id) throws IOException, VetNotFoundException {
        Vet vet = repository.findById(id);

        if (vet != null) {
            vet.setActive(true);
            repository.update(vet);
        } else {
            throw new VetNotFoundException(id);
        }
    }

    //8. Вернуть общее количество ветеринаров в базе данных (только активных)
    public int getActiveVetsNumber() throws IOException {
        return getAllActiveVets().size();
    }

    //9. Вернуть всех ветеринаров раньше работающих в клинике (только не активных)
    public List<Vet> getAllInactiveVets() throws IOException {
        return repository.findAll()
                .stream()
                .filter(x -> !x.isActive())
                .toList();
    }

    //10 Добавить животное, которое лечил ветеринар в список(по id ветеринара и по id питомца)

    public void addNewPetToVet(int vetId, int petId) throws VetNotFoundException, IOException, PetNotFoundException {
        Vet vet = getActiveVetById(vetId);
        Pet pet = petService.getActivePetById(petId);
        vet.getPets().add(pet);
        repository.update(vet);
    }

    //11. Вернуть всех питомцев, которых лечил конкретный ветеринар по id ветеринара
    public List<Pet> getTotalPetsByVetId(int id) throws VetNotFoundException, IOException {
        return getActiveVetById(id).getPets();
    }

//12. Найти ветеринаров по конкретной должности (передаем String role)

    List<Vet> getAllVetByRole(String role) throws IOException {
        return repository.findAll()
                .stream()
                .filter(x -> x.getRole().equals(role))
                .toList();
    }
//13. вернуть все визиты, которые проводил конкретный ветеринар

//14. проверить активность ветеринара по его id, может ли он принимать или уволен
}
