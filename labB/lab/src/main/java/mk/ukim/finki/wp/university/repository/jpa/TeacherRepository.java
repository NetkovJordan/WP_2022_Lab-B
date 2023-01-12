package mk.ukim.finki.wp.university.repository.jpa;

import mk.ukim.finki.wp.university.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAll();
    Optional<Teacher> findById(Long teacherId);
}
