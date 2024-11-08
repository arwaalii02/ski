package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.services.IRegistrationServices;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistrationRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IRegistrationServices registrationServices;

    @InjectMocks
    private RegistrationRestController registrationRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationRestController).build();
    }

    @Test
    void testAddAndAssignToSkier() throws Exception {
        Registration registration = new Registration();
        Long numSkieur = 1L;

        when(registrationServices.addRegistrationAndAssignToSkier(any(Registration.class), eq(numSkieur)))
                .thenReturn(registration);

        mockMvc.perform(put("/registration/addAndAssignToSkier/{numSkieur}", numSkieur)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // Empty JSON assuming Registration has no required fields
                .andExpect(status().isOk());

        verify(registrationServices, times(1)).addRegistrationAndAssignToSkier(any(Registration.class), eq(numSkieur));
    }

    @Test
    void testAssignToCourse() throws Exception {
        Registration registration = new Registration();
        Long numRegistration = 1L;
        Long numCourse = 2L;

        when(registrationServices.assignRegistrationToCourse(numRegistration, numCourse))
                .thenReturn(registration);

        mockMvc.perform(put("/registration/assignToCourse/{numRegis}/{numSkieur}", numRegistration, numCourse)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(registrationServices, times(1)).assignRegistrationToCourse(numRegistration, numCourse);
    }

    @Test
    void testAddAndAssignToSkierAndCourse() throws Exception {
        Registration registration = new Registration();
        Long numSkieur = 1L;
        Long numCourse = 2L;

        when(registrationServices.addRegistrationAndAssignToSkierAndCourse(any(Registration.class), eq(numSkieur), eq(numCourse)))
                .thenReturn(registration);

        mockMvc.perform(put("/registration/addAndAssignToSkierAndCourse/{numSkieur}/{numCourse}", numSkieur, numCourse)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // Empty JSON assuming Registration has no required fields
                .andExpect(status().isOk());

        verify(registrationServices, times(1)).addRegistrationAndAssignToSkierAndCourse(any(Registration.class), eq(numSkieur), eq(numCourse));
    }

    @Test
    void testNumWeeksCourseOfInstructorBySupport() throws Exception {
        Long numInstructor = 1L;
        Support support = Support.ONLINE;
        List<Integer> weeks = Arrays.asList(1, 2, 3);

        when(registrationServices.numWeeksCourseOfInstructorBySupport(numInstructor, support))
                .thenReturn(weeks);

        mockMvc.perform(get("/registration/numWeeks/{numInstructor}/{support}", numInstructor, support)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(weeks.size()));

        verify(registrationServices, times(1)).numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }
}
