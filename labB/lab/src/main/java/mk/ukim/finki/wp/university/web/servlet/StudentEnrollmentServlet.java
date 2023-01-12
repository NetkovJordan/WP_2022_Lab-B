package mk.ukim.finki.wp.university.web.servlet;

import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.Grade;
import mk.ukim.finki.wp.university.model.Student;
import mk.ukim.finki.wp.university.service.CourseService;
import mk.ukim.finki.wp.university.service.GradeService;
import mk.ukim.finki.wp.university.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "enroll_student",urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    private final GradeService gradeService;
    private final StudentService studentService;
    public StudentEnrollmentServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, GradeService gradeService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.gradeService = gradeService;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        HttpSession session = req.getSession();
        resp.setContentType("application/xhtml+xml");
        Long courseId = (Long) session.getAttribute("courseId");
        List<Student> studentList = courseService.listStudentsByCourse(courseId);
        context.setVariable("course",courseService.returnById(courseId));
        context.setVariable("studentsInCourse",studentList);
        Map<String,Grade> grades = new HashMap<>();
        for(Student student : studentList){
            Grade grade = this.gradeService.findByCourseIdAndStudentUsername(courseId,student.getUsername());
            grades.put(student.getUsername(),grade);
        }
        context.setVariable("formatter", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        context.setVariable("grades",grades);
springTemplateEngine.process("studentsInCourse.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long courseId = (Long) session.getAttribute("courseId");
        String student = req.getParameter("Student");
        courseService.addStudentInCourse(student,courseId);
        resp.sendRedirect("/StudentEnrollmentSummary");

    }
}
