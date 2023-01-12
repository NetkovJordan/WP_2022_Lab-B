package mk.ukim.finki.wp.university.service;

import mk.ukim.finki.wp.university.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
        List<Student> listAll();
        List<Student> searchByNameOrSurname(String name,String surname);
        Student save(String username, String password, String name, String surname);

        Student searchByUsername(String username);

       Student login(String username, String password);

        Student register(String name,String surname,String username,String password,String repeatPassword);

}
