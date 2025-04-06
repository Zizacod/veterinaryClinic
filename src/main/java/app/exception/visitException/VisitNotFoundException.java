package app.exception.visitException;

public class VisitNotFoundException extends Exception {

    public VisitNotFoundException(int id) {
        super(String.format("Визит с идентификатором %d не найден", id));
    }

}
