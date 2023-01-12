package mk.ukim.finki.wp.university.service.impl;

import mk.ukim.finki.wp.university.model.Student;
import mk.ukim.finki.wp.university.model.exceptions.*;
import mk.ukim.finki.wp.university.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.university.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String name,String surname) {
        if(name == null || name.isEmpty() && surname == null || surname.isEmpty()){
            throw new IllegalArgumentException();
        }
        return studentRepository.findAllByNameOrSurname(name,surname);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(this.studentRepository.findByUsername(username).isPresent()
        || !this.studentRepository.findByUsername(username).isEmpty())
            throw new StudentAlreadyExistsException(username);


        return studentRepository.save(new Student(username, password, name, surname));
    }

    @Override
    public Student searchByUsername(String username) {

        return studentRepository.findByUsername(username).orElseThrow(() -> new StudentNotFoundException(username));
    }

    @Override
    public Student login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        return this.studentRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(() -> new InvalidUserCredentialsException());
    }

    @Override
    public Student register(String username, String password,String repeatPassword,String name, String surname) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.studentRepository.findByUsername(username).isPresent())
            throw new StudentAlreadyExistsException(username);
        Student student = new Student(username,password,name,surname);
        return this.studentRepository.save(student);
    }
}
