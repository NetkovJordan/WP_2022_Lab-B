package mk.ukim.finki.wp.university.service.impl;

import mk.ukim.finki.wp.university.model.Grade;
import mk.ukim.finki.wp.university.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.university.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> findByCourseId(Long courseID) {
        return gradeRepository.findAllByCourseCourseId(courseID);
    }

    @Override
    public Grade findByCourseIdAndStudentUsername(Long courseID, String username) {
        return gradeRepository.findByCourseCourseIdAndStudentUsername(courseID, username);
    }

    @Override
    public void save(Grade g) {
        gradeRepository.save(g);
    }

    @Override
    public void deleteAll(Iterable<Grade> grades) {
        gradeRepository.deleteAll(grades);
    }
}
