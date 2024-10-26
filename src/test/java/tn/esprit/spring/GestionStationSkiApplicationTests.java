package tn.esprit.spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GestionStationSkiApplicationTests {

	@Mock
	IInstructorRepository instRepository;

	@InjectMocks
	InstructorServicesImpl instServices;

	Instructor s = new Instructor(1L,"amir","boudidah",LocalDate.of(2024, 1, 1));


	List<Instructor> lc = new ArrayList<Instructor>()
	{{
		add(new Instructor(2L,"rayen","alelmi",LocalDate.of(2024, 1, 1)));
		add(new Instructor(3L,"kkkkk","vvvvvv",LocalDate.of(2024, 1, 1)));
		add(new Instructor(4L,"zzzzzz","zzzzzzz",LocalDate.of(2024, 1, 1)));

	}};


	@Test
	void testRetrieveInstructor() {
		when(instRepository.findById(1L)).thenReturn(Optional.of(s));
		Instructor retrievedInstructor = instServices.retrieveInstructor(1L);
		assertNotNull(retrievedInstructor);
		assertEquals(s.getNumInstructor(), retrievedInstructor.getNumInstructor());
		verify(instRepository).findById(1L);
	}
}
 