package app.service;

import app.domain.Owner;
import app.domain.Pet;
import app.domain.Vet;
import app.domain.Visit;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.ownerExceptions.OwnerSaveException;
import app.exception.petExceptions.PetNotFoundException;
import app.exception.vetExceptions.VetNotFoundException;
import app.exception.vetExceptions.VetUpdateException;
import app.exception.visitException.VisitNotFoundException;
import app.exception.visitException.VisitSaveException;
import app.exception.visitException.VisitUpdateException;
import app.repository.VisitRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class VisitService {

    private PetService petService;
    private final VisitRepository repository;


    public VisitService() throws IOException, OwnerNotFoundException, PetNotFoundException {
        repository = new VisitRepository();
        petService = new PetService();
    }


    //1. Добавление визита с указанием питомца, ветеринара, даты и описания симптомов / диагноза, а также владельца
    public Visit save(Visit visit) throws VisitSaveException, IOException {
        if (visit == null) {
            throw new VisitSaveException("Визит не может быть null!");
        }

        LocalDateTime localDateTime = visit.getDate();

        if (localDateTime == null) {
            throw new VisitSaveException("Дата визита не может быть null!");
        }

        String description = visit.getDescription();

        if (description == null || description.trim().isEmpty()) {
            throw new VisitSaveException("Описание визита не может быть null!");
        }

        visit.setActive(true);
        return repository.save(visit);
    }

    //2. Вернуть визит по id (если активен).
    public Visit getActiveVisitById(int id) throws IOException, VisitNotFoundException {
        Visit visit = repository.findById(id);
        if (visit == null || !visit.isActive()) {
            throw new VisitNotFoundException(id);
        }
        return visit;
    }

    //3. Вернуть список всех визитов в базе (только активные).
    public List<Visit> getAllActiveVisits() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Visit::isActive)
                .toList();
    }

    //4. По id питомца вернуть его медицинскую историю (список визитов, отсортированных по дате).
    public List<Visit> getHistoryByPetId(int petId) throws IOException {
        return getAllActiveVisits()
                .stream()
                .filter(x -> x.getPet().getId() == petId)
                .sorted((v1, v2) -> v1.getDate().compareTo(v2.getDate()))
                .toList();
    }

//5. По id ветеринара отобразить все визиты, которые он проводил.

    public List<Visit> getVisitsByVetId(int vetId) throws IOException {
        return getAllActiveVisits()
                .stream()
                .filter(x -> x.getVet().getId() == vetId)
                .toList();
    }

    //6. удалить визит по id визита
    public void deleteById(int id) throws IOException, VisitNotFoundException {
        Visit visit = getActiveVisitById(id);
        visit.setActive(false);
        repository.update(visit);
    }

    //7. восстановить визит по id
    public void restoreById(int id) throws IOException, VisitNotFoundException {
        Visit visit = repository.findById(id);

        if (visit != null) {
            visit.setActive(true);
            repository.update(visit);
        } else {
            throw new VisitNotFoundException(id);
        }
    }

    //8. удалить все визиты питомца по id питомца
    public void deleteAllVisitsByPetId(int petId) throws IOException, VisitNotFoundException {
        Visit visit = repository.findAll()
                .stream()
                .filter(x -> x.getPet().getId() == petId)
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(() -> new VisitNotFoundException(petId));
        repository.update(visit);
    }

    public void update(Visit visit) throws VisitUpdateException, IOException {
        if (visit == null) {
            throw new VisitUpdateException("Визит не может быть null!");
        }

        String description = visit.getDescription();

        if (description == null || description.trim().isEmpty()) {
            throw new VisitUpdateException("Описание визита не может быть null или пустым!");
        }
        visit.setActive(true);
        repository.update(visit);
    }

//9. Получить количество визитов по фильтру(Например: за период, по id врача, по id питомца, по дате.)
//10. получить последние визиты (вернуть n последних визитов)


}


