package mk.ukim.finki.wp.university.service.impl;

import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.Student;
import mk.ukim.finki.wp.university.model.Teacher;
import mk.ukim.finki.wp.university.model.enumeration.Type;
import mk.ukim.finki.wp.university.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.university.model.exceptions.TeacherNotFoundException;

import mk.ukim.finki.wp.university.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.university.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.university.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.university.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository coursesRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(StudentRepository studentRepository, CourseRepository coursesRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.coursesRepository = coursesRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course course = this.coursesRepository.findCourseByCourseId(courseId);

        return course.getStudents();
    }

    @Override
    @Transactional
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = this.studentRepository.findByUsername(username)
                .orElseThrow(() -> new StudentNotFoundException(username));
        Course course = this.coursesRepository.findCourseByCourseId(courseId);
        course.getStudents().add(student);
        return this.coursesRepository.save(course);


    }

    @Override
    public List<Course> listAll() {
        return coursesRepository.findAll();
    }

    @Override
    public Course returnById(Long courseId) {
        return this.coursesRepository.findCourseByCourseId(courseId);
    }

    @Override
    @Transactional
    public Optional<Course> save(String name, String description, Long teacherId,String type) {
    List<Student> students = new ArrayList<>();

    Teacher teacher = this.teacherRepository.findById(teacherId)
            .orElseThrow(() -> new TeacherNotFoundException(teacherId));
    this.coursesRepository.deleteByName(name);
    return Optional.of(this.coursesRepository.save(new Course(name,description,students,teacher, Type.valueOf(type))));

    }

    @Override
    public void deleteById(Long id) {
        this.coursesRepository.deleteById(id);
    }

    @Override
    public Course editCourse(Long id, String name, String description, Long teacherId, String type) {
      Course course = this.coursesRepository.findCourseByCourseId(id);
      if(course!=null){
          course.setName(name);
          course.setDescription(description);
          course.setTeacher(this.teacherRepository.findById(teacherId).get());
          course.setType(Type.valueOf(type));
          this.coursesRepository.save(course);
      }
        return course;
    }  @Override
    public Course save(String name, String description, Long teacher, Long course, String type) {
        Course c = coursesRepository.findById(course).orElse(null);

        if (c == null) {
            c = new Course();
        }

        c.setName(name);
        c.setDescription(description);
        c.setTeacher(teacherRepository.getById(teacher));
        c.setType(Type.valueOf(type));
       return coursesRepository.save(c);
    }


}
