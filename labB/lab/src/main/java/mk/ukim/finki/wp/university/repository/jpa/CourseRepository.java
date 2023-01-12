package mk.ukim.finki.wp.university.repository.jpa;

import mk.ukim.finki.wp.university.model.Course;
import org.hibernate.mapping.Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Course  findCourseByCourseId(Long courseId);

    void deleteByCourseId(Long courseId);

    void deleteByName(String name);

    }
