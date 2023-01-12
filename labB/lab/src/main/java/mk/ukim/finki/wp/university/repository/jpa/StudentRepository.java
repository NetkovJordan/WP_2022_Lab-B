package mk.ukim.finki.wp.university.repository.jpa;


import mk.ukim.finki.wp.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {


List<Student> findAllByNameOrSurname(String name,String surname);
Optional<Student> findByUsername(String username);

Optional<Student> findByUsernameAndPassword(String username,String password);
Student searchStudentByName(String name);
}
