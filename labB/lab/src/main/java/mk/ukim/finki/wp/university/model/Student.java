package mk.ukim.finki.wp.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Student {
@Id

    private String username;
    private String password;
    private String name;
   private String surname;

   @Enumerated(value = EnumType.STRING)
private Role role;


    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;

    }


}
