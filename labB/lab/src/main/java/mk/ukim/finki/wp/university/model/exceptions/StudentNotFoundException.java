package mk.ukim.finki.wp.university.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String username) {
        super(String.format("Student with username: %s doesn't exist",username));
    }
}
