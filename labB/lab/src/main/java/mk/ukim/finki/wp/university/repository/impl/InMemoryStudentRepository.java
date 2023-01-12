package mk.ukim.finki.wp.university.repository.impl;

import mk.ukim.finki.wp.university.bootstrap.DataHolder;
import mk.ukim.finki.wp.university.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryStudentRepository {


        public List<Student> findAllStudents(){
            return DataHolder.studentList;
        }
        public List<Student> findAllByNameOrSurname(String text){
            return DataHolder.studentList.stream().filter(student -> student
                    .getUsername().contains(text) || student.getSurname().contains(text))
                    .collect(Collectors.toList());
        }

        public Student findByUsername(String username){
            return DataHolder.studentList.stream().filter(student -> student.getUsername().equals(username))
                    .findFirst().get();
        }

        public Student addStudent(Student student){
            DataHolder.studentList.add(student);

            return student;
        }



}
