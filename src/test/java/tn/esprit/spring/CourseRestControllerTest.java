package tn.esprit.spring;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.controllers.CourseRestController;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.entities.Support;

import tn.esprit.spring.services.ICourseServices;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ICourseServices courseServices;

    @InjectMocks
    private CourseRestController courseRestController;

    private Course course;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new Course();
        course.setNumCourse(1L);
        course.setLevel(1);
        course.setPrice(100.0f);
        course.setTimeSlot(60);
        course.setTypeCourse(TypeCourse.INDIVIDUAL); 
        course.setSupport(Support.SKI); 
    }

    @Test
    public void testAddCourse() throws Exception {
        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/course/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numCourse\":1,\"level\":1,\"price\":100.0,\"timeSlot\":60,\"typeCourse\":\"INDIVIDUAL\",\"support\":\"SKI\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numCourse").value(1))
                .andExpect(jsonPath("$.level").value(1))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.timeSlot").value(60));
    }

    @Test
    public void testGetAllCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        when(courseServices.retrieveAllCourses()).thenReturn(courses);

        mockMvc.perform(get("/course/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numCourse").value(1))
                .andExpect(jsonPath("$[0].level").value(1))
                .andExpect(jsonPath("$[0].price").value(100.0f))
                .andExpect(jsonPath("$[0].timeSlot").value(60));
    }

@Test
public void testUpdateCourse() throws Exception {
    Course updatedCourse = new Course();
    updatedCourse.setNumCourse(1L);
    updatedCourse.setLevel(1);
    updatedCourse.setPrice(100.0f);
    updatedCourse.setTimeSlot(60);
    updatedCourse.setTypeCourse(TypeCourse.INDIVIDUAL); // Replace with actual enum value
    updatedCourse.setSupport(Support.SKI); // Replace with actual enum value

    when(courseServices.updateCourse(any(Course.class))).thenReturn(updatedCourse);

    String requestBody = "{"
            + "\"numCourse\": 1,"
            + "\"level\": 1,"
            + "\"price\": 100.0,"
            + "\"timeSlot\": 60,"
            + "\"typeCourse\": \"INDIVIDUAL\"," // Ensure the value is correct
            + "\"support\": \"SKI\"" // Ensure the value is correct
            + "}";

    mockMvc.perform(put("/course/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numCourse").value(1))
            .andExpect(jsonPath("$.price").value(100.0))
            .andExpect(jsonPath("$.timeSlot").value(60));
}



    @Test
    public void testGetById() throws Exception {
        when(courseServices.retrieveCourse(1L)).thenReturn(course);

        mockMvc.perform(get("/course/get/{num-course}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numCourse").value(1))
                .andExpect(jsonPath("$.level").value(1))
                .andExpect(jsonPath("$.price").value(100.0));
    }
}
