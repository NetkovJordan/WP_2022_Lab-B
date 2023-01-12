package mk.ukim.finki.wp.university.model.exceptions;


public class StudentAlreadyExistsException extends RuntimeException{
    public StudentAlreadyExistsException(String username) {
        super(String.format("Student with the username: %s already exists.",username));
    }
}

