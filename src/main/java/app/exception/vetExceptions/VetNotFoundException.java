package app.exception.vetExceptions;

public class VetNotFoundException extends Exception {

    public VetNotFoundException(int id) {
        super(String.format("Покупатель с идентификатором %d не найден", id));
    }

    public VetNotFoundException(String name) {
        super(String.format("Покупатель с именем %s не найден", name));
    }
}
