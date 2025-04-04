package app.repository;

import app.domain.Visit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitRepository {

    private final File database;

    private final ObjectMapper mapper;

    private int maxId;

    public VisitRepository() throws IOException {
        database = new File("database/visit.txt");
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Visit> visits = findAll();

        if (!visits.isEmpty()) {
            Visit lastVisits = visits.get(visits.size() - 1);
            maxId = lastVisits.getId();
        }
    }

    public Visit save(Visit visit) throws IOException {
        visit.setId(++maxId);
        List<Visit> visits = findAll();
        visits.add(visit);
        mapper.writeValue(database, visits);
        return visit;
    }

    public List<Visit> findAll() throws IOException {
        try {
            Visit[] visits = mapper.readValue(database, Visit[].class);
            return new ArrayList<>(Arrays.asList(visits));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    public Visit findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

 //  public void update(Vet vet) throws IOException {
//        int id = vet.getId();
//        String newName = vet.getName();
//        boolean active = vet.isActive();
//
//
//        List<Vet> vets = findAll();
//        vets
//                .stream()
//                .filter(x -> x.getId() == id)
//                .forEach(x -> {
//                    x.setName(newName);
//                    x.setActive(active);
//
//                });
//        mapper.writeValue(database, owners);
//    }

    public void deleteById(int id) throws IOException {
        List<Visit> visits = findAll();
        visits.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, visits);
    }
}
