package mk.ukim.finki.wp.university.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND
)
public class CourseIDNotFoundException extends RuntimeException{
    public CourseIDNotFoundException(Long id) {
        super(String.format("Course with id %d was not found",id));
    }
}
