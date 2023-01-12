package mk.ukim.finki.wp.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Grade {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character grade;
@ManyToOne
    private Student student;
@ManyToOne
    private Course course;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeStamp;

    public Grade(Character grade, Student student, Course course, LocalDateTime timeStamp) {
        this.grade = grade;
        this.student = student;
        this.course = course;
        this.timeStamp = timeStamp;
    }


}
