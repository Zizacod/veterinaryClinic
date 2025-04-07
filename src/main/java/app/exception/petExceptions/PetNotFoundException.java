package app.exception.petExceptions;

public class PetNotFoundException extends Exception {

    public PetNotFoundException(int id) {
        super(String.format("Питомец с идентификатором %d не найден", id));
    }

    public PetNotFoundException(String name) {
        super(String.format("Питомец с именем %s не найден", name));
    }
}
