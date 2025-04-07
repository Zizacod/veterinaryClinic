package app.exception.vetExceptions;

public class VetNotFoundException extends Exception {

    public VetNotFoundException(int id) {
        super(String.format("Ветеринар с идентификатором %d не найден", id));
    }

    public VetNotFoundException(String name) {
        super(String.format("Ветеринар с именем %s не найден", name));
    }
}
