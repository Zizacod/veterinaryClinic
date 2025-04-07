package app.exception.ownerExceptions;

public class OwnerNotFoundException extends Exception{

    public OwnerNotFoundException(int id) {
        super(String.format("Владелец с идентификатором %d не найден", id));
    }

    public OwnerNotFoundException(String name) {
        super(String.format("Владелец с именем %s не найден", name));
    }
}
