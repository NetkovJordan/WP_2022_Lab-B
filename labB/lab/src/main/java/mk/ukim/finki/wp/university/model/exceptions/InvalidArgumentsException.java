package mk.ukim.finki.wp.university.model.exceptions;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException() {
        super("Bad credentials!");
    }
}
