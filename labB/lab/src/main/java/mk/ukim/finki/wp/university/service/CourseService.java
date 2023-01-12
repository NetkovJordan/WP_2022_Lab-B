package mk.ukim.finki.wp.university.service;

import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {


    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course returnById(Long courseId);
    Optional<Course> save(String name, String description, Long teacherId,String type);
    void deleteById(Long id);

    Course editCourse(Long id, String name, String description, Long teacher, String type);

    Course save(String name,String description,Long teacher,Long course,String type);

}
