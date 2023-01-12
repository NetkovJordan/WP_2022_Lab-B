package mk.ukim.finki.wp.university.repository.jpa;

import mk.ukim.finki.wp.university.model.Grade;
import mk.ukim.finki.wp.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {

    List<Grade> findAllByCourseCourseId(Long courseID);

    Grade findByCourseCourseIdAndStudentUsername(Long courseID, String username);

    List<Grade> findByTimeStampBetween(LocalDateTime from, LocalDateTime to);

}
