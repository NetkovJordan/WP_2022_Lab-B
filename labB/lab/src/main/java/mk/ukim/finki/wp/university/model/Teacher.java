package mk.ukim.finki.wp.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.university.converter.TeacherFullNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Teacher {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Convert(converter = TeacherFullNameConverter.class)
    private TeacherFullName fullName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;
    public Teacher(String name, String surname) {
        this.fullName = new TeacherFullName(name,surname);
        this.dateOfEmployment = dateOfEmployment;
    }


}
