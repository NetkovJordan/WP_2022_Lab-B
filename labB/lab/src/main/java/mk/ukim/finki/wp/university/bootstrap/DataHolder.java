package mk.ukim.finki.wp.university.bootstrap;

import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.Student;
import mk.ukim.finki.wp.university.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();

   /* @PostConstruct
    public void init(){
        teacherList.add(new Teacher("Teacher1","Surname1"));
        teacherList.add(new Teacher("Teacher2","Surname2"));
        teacherList.add(new Teacher("Teacher3","Surname3"));
        teacherList.add(new Teacher("Teacher4","Surname4"));
        teacherList.add(new Teacher("Teacher5","Surname5"));

        studentList.add(new Student("Dean1","123","Jordan","Netkov"));
        studentList.add(new Student("Dean2","123","Kire","Gurev"));
        studentList.add(new Student("Dean3","123","Ivan","Josifov"));
        studentList.add(new Student("Dean4","123","Pane","Ivanov"));
        studentList.add(new Student("Dean5","123","Boris","Iftimov"));

        List<Student>enroll = new ArrayList<>();
        enroll.add(studentList.get(0));
        enroll.add(studentList.get(2));
        enroll.add(studentList.get(4));

        courseList.add(new Course("Web Programming",
                "SpringBoot and MVC pattern",enroll,teacherList.get(0)));

        enroll.clear();
        enroll.add(studentList.get(1));
        enroll.add(studentList.get(3));
        enroll.add(studentList.get(4));
        courseList.add(new Course("Ethics",
                "Learning of computer ethics",enroll,teacherList.get(1)));

        enroll.clear();
        enroll.add(studentList.get(0));
        enroll.add(studentList.get(1));
        enroll.add(studentList.get(2));
        courseList.add(new Course("Database Management",
                "Learn SQL, DML & DDL",enroll,teacherList.get(2)));

        enroll.clear();
        enroll.add(studentList.get(1));
        enroll.add(studentList.get(2));
        enroll.add(studentList.get(3));
        courseList.add(new Course("Introduction to Data Science",
                "Learn about ML models",enroll,teacherList.get(3)));

        enroll.clear();
        enroll.add(studentList.get(4));
        enroll.add(studentList.get(2));
        enroll.add(studentList.get(1));
        courseList.add(new Course("Visualization","Learn a lot of data charts and how to handle data"
        ,enroll,teacherList.get(4)));
        }
*/

    }

