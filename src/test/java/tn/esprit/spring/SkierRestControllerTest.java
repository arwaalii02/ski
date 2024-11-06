package tn.esprit.spring;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
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
import tn.esprit.spring.controllers.InstructorRestController;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class InstructorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IInstructorServices instructorServices;

    @InjectMocks
    private InstructorRestController instructorRestController;

    private Instructor instructor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        instructor = new Instructor();
        instructor.setNumInstructor(1L);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.of(2022, 1, 1));
    }

    @Test
    public void testAddInstructor() throws Exception {
        when(instructorServices.addInstructor(any(Instructor.class))).thenReturn(instructor);

        mockMvc.perform(post("/instructor/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numInstructor\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"dateOfHire\":\"2022-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numInstructor").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.dateOfHire").value("2022-01-01"));
    }

    @Test
    public void testGetAllInstructors() throws Exception {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(instructor);
        when(instructorServices.retrieveAllInstructors()).thenReturn(instructors);

        mockMvc.perform(get("/instructor/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numInstructor").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].dateOfHire").value("2022-01-01"));
    }

    @Test
    public void testUpdateInstructor() throws Exception {
        Instructor updatedInstructor = new Instructor();
        updatedInstructor.setNumInstructor(1L);
        updatedInstructor.setFirstName("John");
        updatedInstructor.setLastName("Doe");
        updatedInstructor.setDateOfHire(LocalDate.of(2022, 1, 1));

        when(instructorServices.updateInstructor(any(Instructor.class))).thenReturn(updatedInstructor);

        String requestBody = "{"
                + "\"numInstructor\": 1,"
                + "\"firstName\": \"John\","
                + "\"lastName\": \"Doe\","
                + "\"dateOfHire\": \"2022-01-01\""
                + "}";

        mockMvc.perform(put("/instructor/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numInstructor").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.dateOfHire").value("2022-01-01"));
    }

    @Test
    public void testGetById() throws Exception {
        when(instructorServices.retrieveInstructor(1L)).thenReturn(instructor);

        mockMvc.perform(get("/instructor/get/{num-instructor}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numInstructor").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.dateOfHire").value("2022-01-01"));
    }
}
