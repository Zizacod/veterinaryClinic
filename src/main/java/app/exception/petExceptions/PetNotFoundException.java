package app.exception.petExceptions;

public class PetNotFoundException extends Exception {

    public PetNotFoundException(int id) {
        super(String.format("Покупатель с идентификатором %d не найден", id));
    }

    public PetNotFoundException(String name) {
        super(String.format("Покупатель с именем %s не найден", name));
    }
}
