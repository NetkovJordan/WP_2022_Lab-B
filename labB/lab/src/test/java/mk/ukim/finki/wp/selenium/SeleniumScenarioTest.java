package mk.ukim.finki.wp.selenium;


import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.Role;
import mk.ukim.finki.wp.university.model.Student;
import mk.ukim.finki.wp.university.model.Teacher;
import mk.ukim.finki.wp.university.model.enumeration.Type;
import mk.ukim.finki.wp.university.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.university.service.CourseService;
import mk.ukim.finki.wp.university.service.StudentService;
import mk.ukim.finki.wp.university.service.TeacherService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("h2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    private HtmlUnitDriver driver;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;
    private static Teacher t1;
    private static Teacher t2;

    private static Course c1;

    private static Course c2;

    private static Student regularUser;

    private static Student adminUser;

    private static String student = "student";
    private static String admin = "admin";

    private static boolean dataInitialized = false;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        this.driver = new HtmlUnitDriver(true);
       // init();
    }
    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
//    public void init() {
//       if(!dataInitialized){
//           c1 = courseService.save("WEB_PROGRAMMING","DESC_1",1L,100L, "MANDATORY");
//           c2 = courseService.save("DATA_SCIENCE","DESC_2",2L,200L, "WINTER");
//
//           t1 = teacherService.save("TEACHER1","SURNAME1");
//           t2 = teacherService.save("TEACHER2","SURNAME2");
//
//       }
//    }

        @Test
       public void testButtonVisibility() throws Exception{
            setup();
            CoursePage coursePage = CoursePage.to(this.driver);
            coursePage.assertElements(0,0,0,0,0,0);
            LoginPage loginPage = LoginPage.openLogin(this.driver);
            coursePage = LoginPage.doLogin(this.driver,loginPage,"admin","admin");
            coursePage.assertElements(1,1,1,1,1,1);

        }



}
