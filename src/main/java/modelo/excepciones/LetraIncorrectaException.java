package modelo.excepciones;

public class LetraIncorrectaException extends Exception{
    public LetraIncorrectaException() {
        super("La letra del NIF es incorrecta.");
    }
}
