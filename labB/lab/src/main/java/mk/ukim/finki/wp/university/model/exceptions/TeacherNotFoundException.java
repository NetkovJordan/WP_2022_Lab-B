package mk.ukim.finki.wp.university.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(Long id) {
        super(String.format("Teacher with id %d not found",id));
    }
}
