package mk.ukim.finki.wp.university.web.servlet;

import mk.ukim.finki.wp.university.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet (name = "list_courses",urlPatterns = "/listCourses")
public class CourseListServlet extends HttpServlet {

    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;
    public CourseListServlet(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("courseList",courseService.listAll());
        springTemplateEngine.process("listCourses.html",context,resp.getWriter());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String courseId = req.getParameter("courseId");
        session.setAttribute("courseId",Long.valueOf(courseId));
        resp.sendRedirect("/AddStudent");
    }
}
