package mk.ukim.finki.wp.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class TeacherFullName implements Serializable {

    private String name;
    private String surname;

    public TeacherFullName(String name,String surname) {
        this.name = name;
        this.surname = surname;
    }
    @Override
    public String toString(){
        return name + " " + surname;
    }

}
