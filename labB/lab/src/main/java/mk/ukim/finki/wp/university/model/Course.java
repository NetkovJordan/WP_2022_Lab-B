package mk.ukim.finki.wp.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.university.model.enumeration.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
@ManyToMany(fetch = FetchType.EAGER)
   private List<Student> students;
@ManyToOne
   private Teacher teacher;
    @Enumerated(EnumType.ORDINAL)
    private Type type;

    public Course(String name, String description, List<Student> students, Teacher teacher,Type type) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher=teacher;
        this.type = type;
    }


}
