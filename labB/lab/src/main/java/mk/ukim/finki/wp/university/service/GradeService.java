package mk.ukim.finki.wp.university.service;

import mk.ukim.finki.wp.university.model.Grade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GradeService {

    List<Grade> findByCourseId(Long courseID);

    Grade findByCourseIdAndStudentUsername(Long courseID, String username);

    void save(Grade g);

    void deleteAll(Iterable<Grade> grades);
}
