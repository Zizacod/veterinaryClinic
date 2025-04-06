package app.service;

import app.domain.Owner;
import app.domain.Pet;
import app.domain.Vet;
import app.domain.Visit;
import app.exception.ownerExceptions.OwnerSaveException;
import app.exception.visitException.VisitSaveException;
import app.repository.VisitRepository;

import java.io.IOException;
import java.time.LocalDateTime;

public class VisitService {
    private final VisitRepository repository;

    public VisitService() throws IOException {
        repository = new VisitRepository();
    }
//1. Добавление визита с указанием питомца, ветеринара, даты и описания симптомов / диагноза, а также владельца
public Visit save(Visit visit) throws VisitSaveException, IOException {
    if (visit == null){
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


//3. Вернуть список всех визитов в базе (только активные).
//4. По id питомца вернуть его медицинскую историю (список визитов, отсортированных по дате).
//5. По id ветеринара отобразить все визиты, которые он проводил.
//6. удалить визит по id визита
//7. восстановить визит по id
//8. удалить все визиты питомца по id питомца
//9. Получить количество визитов по фильтру(Например: за период, по id врача, по id питомца, по дате.)
//10. получить последние визиты (вернуть n последних визитов)

    }

