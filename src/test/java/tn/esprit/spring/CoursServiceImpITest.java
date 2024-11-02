package tn.esprit.spring;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class CoursServiceImpITest {
    /*
    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private IRegistrationRepository registrationRepository;

    @InjectMocks
    private CourseServicesImpl courseService;

    private Course course;
    private Registration registration;

    @BeforeEach
    public void setup() {
        // Initialize Course object
        course = new Course();
        course.setNumCourse(1L);
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);
        course.setSupport(Support.SKI);
        course.setPrice(100.0f);
        course.setTimeSlot(2);

        // Initialize Registration object
        registration = new Registration();
        registration.setNumRegistration(1L);
    }

    @Test
    public void testRetrieveAllCourses() {
        // Given
        Course course1 = new Course();
        course1.setNumCourse(1L);
        course1.setLevel(1);
        course1.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course1.setSupport(Support.SKI);
        course1.setPrice(100.0f);
        course1.setTimeSlot(2);

        Course course2 = new Course();
        course2.setNumCourse(2L);
        course2.setLevel(2);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);
        course2.setSupport(Support.SNOWBOARD);
        course2.setPrice(150.0f);
        course2.setTimeSlot(3);

        List<Course> courses = Arrays.asList(course1, course2);
        when(courseRepository.findAll()).thenReturn(courses);

        // When
        List<Course> result = courseService.retrieveAllCourses();

        // Then
        assertEquals(2, result.size());
        assertEquals(courses, result);
    }

    @Test
    public void testAddCourse() {
        // Given
        when(courseRepository.save(course)).thenReturn(course);

        // When
        Course result = courseService.addCourse(course);

        // Then
        assertEquals(course, result);
    }

    @Test
    public void testUpdateCourse() {
        // Given
        course.setPrice(120.0f); // updated price
        when(courseRepository.save(course)).thenReturn(course);

        // When
        Course result = courseService.updateCourse(course);

        // Then
        assertEquals(course, result);
    }

    @Test
    public void testRetrieveCourse() {
        // Given
        Long courseId = 1L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // When
        Course result = courseService.retrieveCourse(courseId);

        // Then
        assertEquals(course, result);
    }

    @Test
    public void testAddCourseAndAssignToRegistration() {
        // Given
        when(registrationRepository.findById(1L)).thenReturn(Optional.of(registration));
        when(courseRepository.save(course)).thenReturn(course);

        // When
        Course result = courseService.addCourseAndAssignToregistre(course, 1L);

        // Then
        assertNotNull(result);
        Set<Registration> registrations = result.getRegistrations();
        assertNotNull(registrations);
        assertTrue(registrations.contains(registration));
        assertEquals(1, registrations.size());

        // Verify interactions
        verify(registrationRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(course);
    }

*/
}
