package app.controller;

import app.domain.Owner;
import app.domain.Pet;
import app.domain.Vet;
import app.domain.Visit;
import app.exception.visitException.VisitSaveException;
import app.service.PetService;
import app.service.VisitService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class VisitController {

    private VisitService service;
    private PetService petService;

    public VisitController() throws IOException {
        service = new VisitService();
    }

    /// /    1.Добавление визита (с указанием питомца, ветеринара, даты и описания симптомов / диагноза, а также владельца)

    public Visit save(Owner owner, Pet pet, Vet vet, LocalDateTime date, String description) throws IOException, VisitSaveException {
        Visit visit = new Visit();
        visit.setOwner(owner);
        visit.setPet(pet);
        visit.setVet(vet);
        visit.setDate(date);
        visit.setDescription(description);

        return service.save(visit);
    }

    //    2.Вернуть список всех визитов в базе (только активные).
//
//    public List<Visit> getTotalVisitsById(List<Visit> visits) {
//        return service.getTotalVisitsById(visits);
//    }
//
//    3.Вернуть визит по id (если активен).
//
//    public List<Visit> getAllActiveVisits() {
//        return service.getAllActiveVisits();
//    }
//

//
////    4.По id питомца вернуть его медицинскую историю (список визитов, отсортированных по дате).
//
//    public List<Visit> getPetVisitsByPetId(int petId) {
//        List<Visit> visits = repository.findAllByPetIdVisitByDate(petId);
//
//        if (visits.isEmpty()) {
//            throw new VisitNotFoundException(petId);
//        }
//
//        return visits;
//    }
//
////    5.По id ветеринара отобразить все визиты, которые он проводил.
//
//    public List<Visit> getActiveVisitsByVetId(int vetId) {
//        List<Visit> visits = repository.findByVetId(vetId);
//
//        if (visits.isEmpty()) {
//            throw new VisitNotFoundException(vetId);
//        }
//
//        return visits.stream()
//                .filter(Visit::isActive)
//                .collect(Collectors.toList());
//    }
//
////    6.удалить визит по id визита
//
//    public void deleteById(int id) {
//        Visit visit = getActiveVisitById(id);
//        visit.setActive(false);
//        repository.update(visit);
//    }
//
////   Удалить визит из базы данных по его имени
//
//    public void deleteByName(String name) {
//        Visit visit = getAllActiveVisits()
//                .stream()
//                .filter(x -> x.getName().equals(name))
//                .peek(x -> x.setActive(false))
//                .findFirst()
//                .orElseThrow(() -> new VisitNotFoundException(name));
//        repository.update(visit);
//    }
//
////    7.восстановить визит по id
//
//    public void restoreById(int id) {
//        Visit visit = repository.findById(id);
//
//        if (visit != null) {
//            visit.setActive(true);
//            repository.update(visit);
//        } else {
//            throw new VisitNotFoundException(id);
//        }
//    }
//
////    8.удалить все визиты питомца по id питомца
//
//    public int getActiveVisitsNumber() {
//        return getAllActiveVisits().size();
//    }
//
//    //    9.Получить количество визитов по фильтру(Например: за период, по id врача, по id питомца, по дате.)
//    // может вернуть просто всего количество визитов?
//
//    public int getALLActiveVisitsNumber() {
//        return getAllActiveVisitsNumber().size();
//    }
//
////    10.получить последние визиты (вернуть n последних визитов)
//    // какие последние: питомца, владельца, ветеринара, по дате?
}

