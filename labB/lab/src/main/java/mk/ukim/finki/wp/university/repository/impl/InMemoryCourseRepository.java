package mk.ukim.finki.wp.university.repository.impl;


import mk.ukim.finki.wp.university.bootstrap.DataHolder;
import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.Student;
import mk.ukim.finki.wp.university.model.Teacher;
import mk.ukim.finki.wp.university.model.enumeration.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCourseRepository {

    private final InMemoryStudentRepository studentRepository;

    public InMemoryCourseRepository(InMemoryStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Course> findAllCourses(){
        return DataHolder.courseList;
    }
    public Optional<Course> findById(Long courseId){
        return DataHolder.courseList.stream().filter(course -> course.getCourseId().equals(courseId)).findAny();
    }
    public List<Student> findAllStudentsByCourse(Long courseId){
        Course course = DataHolder.courseList.stream().filter(course1 -> course1.getCourseId().equals(courseId)).findFirst().get();
        return course.getStudents();
    }
    public Course addStudentToCourse(Student student, Course course){
        List<Student> students = course.getStudents();
        students.removeIf(student1 -> student1.getUsername().equals(student.getUsername()));
        students.add(student);
        course.setStudents(students);
        return course;

    }
    public void delete(Long courseId){
        DataHolder.courseList.removeIf(course -> course.getCourseId().equals(courseId));
    }
    public Optional<Course> save(String name, String description, Long teacherId,String type){
        DataHolder.courseList.removeIf(course -> course.getName().equals(name));
        List<Student> empty = new ArrayList<>();
        Teacher teacher = DataHolder.teacherList.stream().filter(
                teacher1 -> teacher1.getId().equals(teacherId)
        ).findFirst().get();
        Course course = new Course(name,description,empty,teacher, Type.valueOf(type));
        DataHolder.courseList.add(course);
        return Optional.of(course);
    }
}
