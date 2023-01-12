package mk.ukim.finki.wp.university.web.controller;

import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.Grade;
import mk.ukim.finki.wp.university.model.Student;
import mk.ukim.finki.wp.university.model.Teacher;
import mk.ukim.finki.wp.university.service.CourseService;
import mk.ukim.finki.wp.university.service.GradeService;
import mk.ukim.finki.wp.university.service.StudentService;
import mk.ukim.finki.wp.university.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private static final List<String> types = List.of("WINTER", "SUMMER", "MANDATORY", "ELECTIVE");
    private final CourseService courseService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final GradeService gradeService;
    public CourseController(CourseService courseService, StudentService studentService, TeacherService teacherService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){

        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("errorMessage","That ID doesn't exist");
        }
        else{
            model.addAttribute("hasError",false);
        }
        model.addAttribute("courses",this.courseService.listAll());

        return "listCourses";

    }

@GetMapping("/add")
@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String renderAddCourse(Model model){
    List<Teacher> teacherList = this.teacherService.findAll();
    model.addAttribute("teachers",teacherList);
    model.addAttribute("types",types);
    return "addCourse";
}
@PostMapping("/add")
public String saveCourse(@RequestParam String name,@RequestParam String description,@RequestParam Long teacherId,@RequestParam String typeOfCourse){
        this.courseService.save(name,description,teacherId,typeOfCourse);
        return "redirect:/course";
}
    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {

        if (this.courseService.returnById(id)!=null) {
            Course course = this.courseService.returnById(id);

            List<Teacher> teacherList = this.teacherService.findAll();

            model.addAttribute("teachers", teacherList);
            model.addAttribute("course", course);
            model.addAttribute("types",types);

            return "addCourse";
        }
        return "redirect:/course?error=CourseNotFound";
    }
    @PostMapping("/add/{id}")
    public String editCourse(@PathVariable Long id,@RequestParam String name,@RequestParam String description,@RequestParam Long teacherId,@RequestParam String typeOfCourse){
        this.courseService.editCourse(id,name,description,teacherId,typeOfCourse);
        return "redirect:/course";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        List<Grade> grades = this.gradeService.findByCourseId(id);
        this.gradeService.deleteAll(grades);
        this.courseService.deleteById(id);
        return "redirect:/course";
    }
    @GetMapping("/addGrade")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddGradePage(Model model) {
        model.addAttribute("courses", this.courseService.listAll());
        model.addAttribute("students", this.studentService.listAll());

        return "addGrade";
    }

    @PostMapping("/addGrade")
    public String saveGrade(@RequestParam String student,@RequestParam Long courseId,  @RequestParam String grade, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        Student s = this.studentService.searchByUsername(student);
        Course c = this.courseService.returnById(courseId);
        Grade g = new Grade(Character.valueOf(grade.charAt(0)), s, c, date);

        this.gradeService.save(g);

        return "redirect:/course";
    }
}
