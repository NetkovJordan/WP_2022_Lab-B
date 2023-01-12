package mk.ukim.finki.wp.university.web.servlet;

import mk.ukim.finki.wp.university.service.CourseService;
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

@WebServlet(name = "student-found",urlPatterns = "/showStudent")
public class studentFoundServlet extends HttpServlet {
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;
    public studentFoundServlet(StudentService studentService, CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;

        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        HttpSession session = req.getSession();
        Long courseId = (Long) session.getAttribute("courseId");
        context.setVariable("courseName",courseService.returnById(courseId));
        springTemplateEngine.process("findStudent.html",context,resp.getWriter());
    }


}
