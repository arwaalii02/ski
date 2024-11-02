package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CourseServicesImpl implements  ICourseServices{
private IRegistrationRepository registrationRepository;
    private ICourseRepository courseRepository;

    @Override
    public List<Course> retrieveAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course retrieveCourse(Long numCourse) {
        return courseRepository.findById(numCourse).orElse(null);
    }
    @Override
    public Course addCourseAndAssignToregistre(Course course, Long numRegistration) {
        Registration registre = registrationRepository.findById(numRegistration).orElse(null);
        Set<Registration> registreSet = new HashSet<>();
        registreSet.add(registre);
        course.setRegistrations(registreSet);
        return courseRepository.save(course);
    }

}
