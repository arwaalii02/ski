package tn.esprit.spring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.controllers.CourseRestController;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.services.ICourseServices;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

////////////////////
public class CoursControllerTest {
    /*
    @Mock
    private ICourseServices courseServices;

    @InjectMocks
    private CourseRestController courseRestController;

    private MockMvc mockMvc;

    private Course course;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(courseRestController).build();

        // Initializing a test course
        course = new Course();
        course.setNumCourse(1L);
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);
        course.setPrice(100.0f);
    }

    @Test
    public void testAddCourse() throws Exception {
        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/course/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"));

        verify(courseServices, times(1)).addCourse(any(Course.class));
    }

    @Test
    public void testAddAndAssignToRegistration() throws Exception {
        when(courseServices.addCourseAndAssignToregistre(any(Course.class), eq(1L))).thenReturn(course);

        mockMvc.perform(put("/course/addAndAssignToRegistration/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"));

        verify(courseServices, times(1)).addCourseAndAssignToregistre(any(Course.class), eq(1L));
    }

    @Test
    public void testGetAllCourses() throws Exception {
        List<Course> courses = Arrays.asList(course);

        when(courseServices.retrieveAllCourses()).thenReturn(courses);

        mockMvc.perform(get("/course/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}]"));

        verify(courseServices, times(1)).retrieveAllCourses();
    }

    @Test
    public void testUpdateCourse() throws Exception {
        when(courseServices.updateCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(put("/course/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"));

        verify(courseServices, times(1)).updateCourse(any(Course.class));
    }

    @Test
    public void testAssignCourseToRegistration() throws Exception {
        when(courseServices.addCourseAndAssignToregistre(any(Course.class), eq(1L))).thenReturn(course);

        mockMvc.perform(post("/course/course/1/assign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numCourse\":1,\"level\":1,\"typeCourse\":\"COLLECTIVE_ADULT\",\"price\":100.0}"));

        verify(courseServices, times(1)).addCourseAndAssignToregistre(any(Course.class), eq(1L));
    }
    */
}
