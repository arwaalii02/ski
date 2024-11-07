package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GestionStationSkiApplicationTests {

	@Mock
	IInstructorRepository instRepository;

	@Mock
	ICourseRepository courseRepository;

	@InjectMocks
	InstructorServicesImpl instServices;

	Instructor s = new Instructor(1L, "amir", "boudidah", LocalDate.of(2024, 1, 1));
	// Create an empty set of registrations for Course initialization
	Set<Registration> registrations = new HashSet<>();
	Course c = new Course(1L, 2, TypeCourse.COLLECTIVE_CHILDREN, Support.SKI, 100.0f, 5, registrations); // Updated constructor to include Set<Registration>
	List<Instructor> lc = new ArrayList<Instructor>() {{
		add(new Instructor(2L, "rayen", "alelmi", LocalDate.of(2024, 1, 1)));
		add(new Instructor(3L, "kkkkk", "vvvvvv", LocalDate.of(2024, 1, 1)));
		add(new Instructor(4L, "zzzzzz", "zzzzzzz", LocalDate.of(2024, 1, 1)));
	}};

	

}
