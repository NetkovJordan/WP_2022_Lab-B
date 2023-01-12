package mk.ukim.finki.wp;

import mk.ukim.finki.wp.university.model.Course;
import mk.ukim.finki.wp.university.model.enumeration.Type;
import mk.ukim.finki.wp.university.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.university.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.university.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.university.service.CourseService;
import mk.ukim.finki.wp.university.service.StudentService;
import mk.ukim.finki.wp.university.service.TeacherService;
import mk.ukim.finki.wp.university.service.impl.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;



@RunWith(MockitoJUnitRunner.class)
public class CourseEditingTest {

    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TeacherRepository teacherRepository;
    private Object mockMvc;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Course course = new Course();
        course.setCourseId(43L);
        course.setName("WEB");
        course.setDescription("DESC_X");
        course.setTeacher(null);
        course.setType(Type.MANDATORY);

        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(courseRepository.findById(43L)).thenReturn(Optional.of(course));
        courseService = Mockito.spy(new CourseServiceImpl(studentRepository, courseRepository, teacherRepository));
    }

    @Test
    public void testEditExistentCourse() {
        Course course = new Course();
        course.setCourseId(43L);
        course.setName("WEB");
        course.setDescription("DESC_X");
        course.setTeacher(null);
        course.setType(Type.MANDATORY);

        Course rs = courseService.editCourse(43L, "WEB", "DESC_X", null, "MANDATORY");
        Mockito.verify(courseService).editCourse(43L, "WEB", "DESC_X", null, "MANDATORY");

        Assert.assertNotEquals(course, rs);
    }

    @Test
    public void testEditNonExistentCourse() {
        Course c = courseService.editCourse(2L, "Course", "Description", null, "MANDATORY");
        Mockito.verify(courseService).editCourse(2L, "Course", "Description", null, "MANDATORY");

        Assert.assertNull(c);
    }
}